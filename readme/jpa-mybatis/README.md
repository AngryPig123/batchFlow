# JPA + MyBatis config

## 1. 배경 (Why)

본 프로젝트는 주문/결제 도메인을 포함한 배치 중심 시스템이며,
초기에는 기능 개발 속도와 잦은 변경 대응이 중요하고,
중장기적으로는 집계/리포트 및 DW 성격의 작업 비중이 커질 것으로 예상됩니다.

- 초기 개발: CRUD/업무 로직 중심, 스키마/요구사항 변경 빈번 → 생산성 우선
- 중장기 운영: 통계/집계/리포트/정산 확장, 성능 튜닝 및 SQL 통제가 중요 → 조회 최적화 우선

따라서 단일 데이터 접근 기술로 밀기보다,
**생산성과 확장성을 동시에 확보하기 위해 역할을 분리**합니다.

---

## 2. 선택한 전략 (What)

- **JPA (Repository)**
    - 초기 개발 생산성 확보(빠른 CRUD 개발, 변경 대응 용이)
    - 트랜잭션 및 도메인 상태 변경 처리(주문/결제 등)
    - 도메인 모델 기반의 응집도 유지

- **MyBatis (Mapper)**
    - 통계/집계/리포트 조회 및 배치 집계 SQL에 집중
    - 복잡한 조인/집계 SQL의 명시적 제어 및 성능 튜닝 용이
    - 향후 DW/분석 목적 쿼리 확장에 유리

- DDL 관리는 `flyway`를 이용하여 하며 `ddl-auto` 설정은 `validate`를 유지한다.

---

## 3. 패키지 구조 (How)

```text
com.batchflow
├── domain
│   ├── order
│       ├── controller
│       ├── dto
│       ├── entity
│       ├── service
│       ├── repository (JPA)
│       ├── mapper (MyBatis)
│       ├── ...
│       ├── ...
│       └── ...
│
├── batch
│   ├── aggregation
│       ├── repository (JPA)
│       ├── mapper (MyBatis)
│       ├── job
│       ├── tasklet
│       ├── dto
│       ├── entity
│       ├── service
│       ├── ...
│       ├── ...
│       └── ...
│
└── config
    ├── JpaConfig
    └── MyBatisConfig
