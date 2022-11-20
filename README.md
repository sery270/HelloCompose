# HelloCompose
Compose 공부하는 레포지토리입니다 :) 주로 코드랩과 공식문서 이해한 내용을 정리합니다.

### 🎨 2022/11/20 일 

#### Declarative UI
- rx 모델 + Kotlin 의 결과물
- data -> UI 로 변환하는 함수를 호출하여 UI를 표현함
  - 그 함수가 Composable 함수
- (rx 모델이 적용되었기 때문에) data 가 변경되면 자동으로 UI 에 반영됨
  - FW가 Composable 함수를 자동으로 다시 실행하는 방식으로 (recomposition)

#### Composable function
- compose app 은 Composable function 로 구성됨 
  - @Composable 가 붙은 함수
- Composable function 은 Composable function 에서만 호출 가능
- Composable function 을 줄여서 Composable 이라고 부름 
