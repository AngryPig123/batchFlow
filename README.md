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
