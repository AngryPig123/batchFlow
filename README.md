## Project Overview

본 프로젝트는 **Kaggle에서 제공하는 브라질 이커머스 플랫폼 데이터셋(Olist Dataset)** 을 기반으로,  
CSV 형태의 원천 데이터를 관계형 데이터베이스 스키마로 재구성하고  
이를 활용한 **배치 처리 및 집계, 도메인 확장**을 목표로 합니다.

---

### 프로젝트 목적

- Olist 데이터셋(CSV)을 기준으로 **DDL 생성 및 구조 조정**
- 초기 **Seed 데이터 적재 배치** 구현
- 배치 처리 흐름을 기반으로 점진적인 **도메인 모델 확장**
- 단순 데이터 적재를 넘어, **실제 이커머스 도메인 관점의 테이블 설계 및 활용**까지 확장

초기 단계에서는 데이터 적재와 배치 구조에 집중하고,  
이후 테이블 추가 및 구조 확장을 통해 **FO(Front Office) 레벨에서도 활용 가능한 도메인 모델**로 발전시키는 것을 목표로 합니다.

---

### 데이터 소스

- **Kaggle**: Brazilian E-Commerce Public Dataset by Olist
- **제공 형식**: CSV
- **활용 방식**
  - CSV → 테이블 DDL 정의
  - Flyway 기반 스키마 버전 관리
  - Seed 데이터 적재용 배치 구현

---

## 정산/통계 요구사항 (데이터 관점)

본 문서는 **OLIST 스키마(Orders / OrderItems / Payments / Reviews / Products / Customers / Sellers / CategoryTrans / Geolocation)** 를 기반으로,
향후 집계 테이블 설계, 배치 집계, 통계 쿼리 작성을 위한 **데이터 요구사항**을 정의한다.

---

### 1. 공통 전제 및 정의

#### 1.1 시간 기준(Date Dimension)
정산/통계 산출 시 기준일은 아래 중 선택 가능해야 한다.

- **PURCHASE_DATE**: `OLIST_ORDERS.ORDER_PURCHASE_TIMESTAMP`
- **APPROVED_DATE**: `OLIST_ORDERS.ORDER_APPROVED_AT`
- **DELIVERED_DATE**: `OLIST_ORDERS.ORDER_DELIVERED_CUSTOMER_DATE` *(정산/매출 인식 기준으로 권장)*

> 모든 집계는 `TRUNC(timestamp)` 기준의 **일 단위**로 재집계 가능하도록 설계한다.

#### 1.2 주문 상태(Status)
원천 상태는 `OLIST_ORDERS.ORDER_STATUS`를 사용하며,
통계 목적상 아래 그룹핑이 가능해야 한다.

- **성공(매출 인식)**: `delivered`
- **진행 중**: `created`, `approved`, `invoiced`, `processing`, `shipped`
- **실패/무효**: `canceled`, `unavailable`

#### 1.3 금액 정의(Amount Metrics)
- **GMV(상품매출)** = `SUM(OLIST_ORDER_ITEMS.PRICE)`
- **FREIGHT(배송비)** = `SUM(OLIST_ORDER_ITEMS.FREIGHT_VALUE)`
- **REVENUE(총매출)** = `GMV + FREIGHT`
- **PAYMENT(결제금액)** = `SUM(OLIST_ORDER_PAYMENTS.PAYMENT_VALUE)`

> `PAYMENT`는 주문 단위 데이터이며, 판매자/카테고리 단위로 확장 시 **배분 규칙**이 필요하다.

#### 1.4 결제 배분 규칙(필수 결정)
결제 데이터는 주문 단위이므로, seller/category 단위 정산을 위해 아래 중 하나를 채택한다.

- (권장) **매출 비율 배분**
  - `seller_revenue / order_total_revenue * order_payment`
- (단순) **라인 아이템 수 비율 배분**
  - `seller_items / order_items * order_payment`
- (참고값) **배분하지 않고 주문 결제만 별도 지표로 사용**
  - seller 단위 테이블에 payment 제외

---

### 2. 보고 싶은 통계(목적)와 필요한 데이터

아래 항목들은 “어떤 관점의 정산/통계를 보고 싶고”를 정의하며,
각 항목별로 **필요 데이터(원천 테이블/컬럼)** 를 명시한다.

---

## A. 기간별 총괄 정산 요약

### 목적
- 특정 기간의 전체 실적(주문/매출/결제)을 빠르게 산출
- 상태별(배송완료 vs 취소/미배송) 규모 확인
- 매출(배송완료 기준)과 결제(승인 기준)의 차이 관찰

### 필요 데이터
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `CUSTOMER_ID`, `ORDER_STATUS`
  - `ORDER_PURCHASE_TIMESTAMP`, `ORDER_APPROVED_AT`, `ORDER_DELIVERED_CUSTOMER_DATE`
- 주문 라인: `OLIST_ORDER_ITEMS`
  - `ORDER_ID`, `PRICE`, `FREIGHT_VALUE`
- 결제: `OLIST_ORDER_PAYMENTS`
  - `ORDER_ID`, `PAYMENT_VALUE`, `PAYMENT_TYPE`, `PAYMENT_INSTALLMENTS`

### 산출 지표 예시
- 주문수(`COUNT(DISTINCT order_id)`)
- 라인수(`COUNT(*) on order_items`)
- GMV / FREIGHT / REVENUE
- 결제금액 합(PAYMENT_VALUE 합)
- 상태별 주문수/매출

---

## B. 판매자 단위 정산(핵심)

### 목적
- seller별 매출 및 주문 기여도 산출
- seller별 정산 대상 규모 파악
- 결제금액을 seller에 배분하여 “정산 관점”으로 조회 가능하게 함

### 필요 데이터
- 판매자: `OLIST_SELLERS`
  - `SELLER_ID`, `SELLER_CITY`, `SELLER_STATE`
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `ORDER_STATUS`, (선택한 기준일 timestamp)
- 주문 라인: `OLIST_ORDER_ITEMS`
  - `ORDER_ID`, `SELLER_ID`, `PRICE`, `FREIGHT_VALUE`
- 결제: `OLIST_ORDER_PAYMENTS`
  - `ORDER_ID`, `PAYMENT_VALUE` *(배분 시 필요)*

### 추가 파생 데이터(배치에서 계산 권장)
- `order_total_revenue` (주문 전체 REVENUE)
- `seller_revenue` (주문 내 seller별 REVENUE)
- `seller_payment_alloc` (배분 규칙 기반 seller 결제 배분값)

### 산출 지표 예시
- seller별 주문수, 라인수
- seller별 GMV/FREIGHT/REVENUE
- seller별 결제 배분액(선택)
- 지역별(seller_state/city) 분s
- 기간별 추이(일/주/월 재집계 가능)

---

## C. 카테고리 단위 매출/정산

### 목적
- 카테고리별 매출/비중 파악
- 특정 카테고리의 성장/감소 추이 분석
- 카테고리 이름 번역(영문명)으로 리포팅 가능하게 함

### 필요 데이터
- 상품: `OLIST_PRODUCTS`
  - `PRODUCT_ID`, `PRODUCT_CATEGORY_NAME`
- 카테고리 번역: `OLIST_PRODUCT_CAT_TRANS`
  - `PRODUCT_CATEGORY_NAME`, `PRODUCT_CATEGORY_NAME_ENGLISH`
- 주문 라인: `OLIST_ORDER_ITEMS`
  - `ORDER_ID`, `PRODUCT_ID`, `PRICE`, `FREIGHT_VALUE`
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `ORDER_STATUS`, (선택한 기준일 timestamp)

### 산출 지표 예시
- category별 주문수/라인수
- category별 GMV/FREIGHT/REVENUE
- category별 매출 비중(%) 및 순위

---

## D. 결제수단/할부 분석 (현금흐름 관점)

### 목적
- 결제수단별 결제금액/비중 확인
- 할부 개월 수 분포(installments) 분석
- 주문 수와 결제금액의 관계 파악

### 필요 데이터
- 결제: `OLIST_ORDER_PAYMENTS`
  - `ORDER_ID`, `PAYMENT_TYPE`, `PAYMENT_INSTALLMENTS`, `PAYMENT_VALUE`
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `ORDER_STATUS`, `ORDER_APPROVED_AT` *(결제 기준일로 권장)*

### 산출 지표 예시
- payment_type별 결제금액 합, 주문수
- installments별 결제금액/주문수 분포
- 결제수단별 평균 결제금액

---

## E. 지역 단위 성과(고객/판매자 관점)

### 목적
- 고객 지역별 매출/주문 분포(수요)
- 판매자 지역별 매출/주문 분포(공급)
- 수요/공급 지역의 불균형 탐지

### 필요 데이터
- 고객: `OLIST_CUSTOMERS`
  - `CUSTOMER_ID`, `CUSTOMER_CITY`, `CUSTOMER_STATE`, `CUSTOMER_ZIP_CODE_PREFIX`
- 판매자: `OLIST_SELLERS`
  - `SELLER_ID`, `SELLER_CITY`, `SELLER_STATE`, `SELLER_ZIP_CODE_PREFIX`
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `CUSTOMER_ID`, `ORDER_STATUS`, (선택한 기준일 timestamp)
- 주문 라인: `OLIST_ORDER_ITEMS`
  - `ORDER_ID`, `SELLER_ID`, `PRICE`, `FREIGHT_VALUE`

> `OLIST_GEOLOCATION`은 ZIP_PREFIX가 중복되므로, 필요 시 "대표 좌표"를 뽑는 규칙(평균/최빈)을 추가 정의해야 한다.

### 산출 지표 예시
- 고객 state/city별 주문수/REVENUE
- 판매자 state/city별 주문수/REVENUE
- 고객 지역 vs 판매자 지역 교차(선택: 배송 흐름 분석)

---

## F. 리뷰 품질 지표 (선택)

### 목적
- 리뷰 점수 분포 및 평균 점수 산출
- 매출 규모와 리뷰 품질의 관계 탐색(카테고리/판매자 단위)
- 배송 리드타임과 리뷰 점수의 상관 확인(선택)

### 필요 데이터
- 리뷰: `OLIST_ORDER_REVIEWS`
  - `REVIEW_ID`, `ORDER_ID`, `REVIEW_SCORE`, `REVIEW_CREATION_DATE`
- 주문: `OLIST_ORDERS`
  - `ORDER_ID`, `ORDER_STATUS`, 배송 관련 timestamp
- 주문 라인: `OLIST_ORDER_ITEMS`
  - `ORDER_ID`, (옵션: seller/category로 확장)

### 산출 지표 예시
- score별 리뷰수, 평균 점수
- seller/category별 평균 리뷰 점수
- 배송 리드타임 구간별 평균 리뷰 점수

---

### 3. 집계 테이블 설계 방향(요구사항 수준)

#### 3.1 공통 요구사항
- 일 단위(`STAT_DATE`)로 적재하고 필요 시 월/주로 재집계 가능해야 함
- 필터 조건(상태/결제수단/카테고리/지역)이 집계 결과에서 조회 가능해야 함
- 재처리 가능해야 함(특정 기간 delete+insert 또는 merge)

---

### 패키지 구조 개요

프로젝트의 주요 패키지는 역할 기준으로 다음과 같이 구성됩니다.

- **batch**
  - 배치 처리 로직
  - Seed 데이터 적재, 집계, 변환 등 배치 작업 담당

- **bootstrap**
  - 초기 데이터 구성
  - CSV 기반 Seed 데이터 로딩 및 초기화 로직

- **domain**
  - 이커머스 도메인 모델
  - 추후 FO 레벨에서 재사용 가능한 엔티티 및 도메인 로직
  - 테이블 확장 시 도메인 중심으로 구조 확장 예정

---

## Branch Strategy (main / dev / release-YYYYMMDD)

이 프로젝트는 `main` 브랜치의 안정성을 최우선으로 하기 위해,
GitHub Flow를 참고하되 **통합(dev)과 배포(release)를 분리한 3-브랜치 전략**을 사용합니다.

본 전략에서는 기능 개발과 배포 대상을 명확히 분리하여,
배포 범위 통제와 운영 안정성을 동시에 확보하는 것을 목표로 합니다.

---

### Branch Roles

- **main**
    - 운영(Production) 기준 브랜치
    - 항상 배포 가능한 상태를 유지
    - 직접 push 금지(권장), PR을 통해서만 병합

- **dev**
    - 기능 통합(Integration) 및 사전 검증 브랜치
    - 모든 기능 브랜치의 1차 병합 대상
    - 다음 릴리즈를 준비하는 지속 통합 브랜치

- **release-YYYYMMDD**
    - 특정 날짜의 배포 후보 스냅샷 브랜치
    - `dev`에서 **배포 대상으로 확정된 기능만** 반영
    - 최종 검증 후 `main`에 병합

---

### Workflow

#### 1) 기능 개발
- 개발자는 최신 `main`을 기준으로 기능 브랜치를 생성합니다.
- 기능 구현 완료 후 PR을 통해 `dev`에 병합합니다.

#### 2) dev 통합 및 검증
- `dev`에는 여러 기능 브랜치가 지속적으로 병합됩니다.
- 기본적인 통합 테스트 및 기능 검증을 수행합니다.

#### 3) 릴리즈 브랜치 생성
- 배포가 결정되면, `main` 기준으로 `release-YYYYMMDD` 브랜치를 생성합니다.

#### 4) 배포 대상 기능 반영 (중요)
- `dev`에 병합된 기능 중 **배포 대상으로 확정된 기능만**
  `release-YYYYMMDD`에 반영합니다.

> 이 규칙을 통해 `release` 브랜치는 항상 배포 범위가 명확한 상태를 유지합니다.

#### 5) release 검증 및 배포
- `release-YYYYMMDD`에서 최종 스모크 테스트 및 배치 실행 검증을 수행합니다.
- 검증 완료 후 관리자가 PR을 통해 `main`에 병합합니다.

---

### 동화기화 규칙 (필수!!)

`release-YYYYMMDD`가 `main`에 병합된 이후에는,
반드시 해당 변경 사항을 `dev` 브랜치에도 다시 반영합니다.

---

## ERD
### ERD 확인 플러그인 및 익스텐션

- ERD는 IDE 플러그인/익스텐션 기반으로 관리합니다.
개발자는 IntelliJ IDEA 또는 Visual Studio Code에서 ERD 파일을 직접 열어 구조를 확인하며,
ERD는 설계 산출물의 일부로서 Git 저장소에 함께 버전 관리됩니다.
IntelliJ 사용자는 ERD Editor 플러그인을 통해 ERD를 확인합니다.
  - `flyway`를 통해 DB 스키마 버전 변경이 발생할 경우, 해당 변경 내용을 `batchFlow.erd` 파일에도 동기화합니다.
  - `VS Code` 사용자는 `vuerd-vscode` 익스텐션을 통해 동일한 ERD를 확인합니다.
  - ERD 수정 사항은 코드 변경과 동일하게 PR 단위로 리뷰 및 이력 관리합니다.

- intellij : https://plugins.jetbrains.com/plugin/23594-erd-editor
- visual studio code : https://marketplace.visualstudio.com/items?itemName=dineug.vuerd-vscode
