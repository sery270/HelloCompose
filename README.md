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

#### Theme
- 예를 들면, Surface 의 color 와 Text 의 color 의 관계를 정의 해주는 곳
  - 어두운 배경이면 밝은 텍스트, 다크모드 대응

#### modifier
- 상위 요소 레이아웃 내에서 UI 요소가 배치되고 표시되고 동작하는 방식 정의 
- 정렬, 애니메이션 처리, 배치, 클릭 가능 여부 또는 스크롤 가능 여부 지정, 변환 등에 사용
- 함수 내에서 호출하는 첫 번째 컴포저블로 modifier 를 생성하여 전달하여,
  - 컴포저블 외부에서 레이아웃 안내와 동작을 조정할 수 있음

#### Composable layouts
![image](https://user-images.githubusercontent.com/59532818/202907671-42965111-7d45-4db9-a92f-9ce92575b436.png)
- Column, Row, Box
  - Linear layout -> Column, Row
  - Frame layout -> Box
