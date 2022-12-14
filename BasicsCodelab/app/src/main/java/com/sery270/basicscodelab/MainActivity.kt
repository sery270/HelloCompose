package com.sery270.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sery270.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme { // setContent 내에서 사용되는 앱 테마는 프로젝트 이름에 맞게 지정됩니다.
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {
    // remember 함수는 컴포저블이 컴포지션에 유지되는 동안에만 작동 (스크롤 되어 넘어가면 날라감 ex. expanded)
    // rememberSaveable 함수는 구성 변경시에도 유지되는 동안에만 작동
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {

    // 1. 각 아이템 (greeting) 마다 확장 여부 정보가 필요하다. 논리적으로 expanded 정보는 각 아이템이 가지고 있어야한다.
    // 2. Boolean 같은 자료형으로 상태를 저장하면 안된다.
    // var expanded = false
    // 3. Composable에 내부 상태를 저장하기 위해서 mutableStateOf 를 사용해야한다.
    // val expanded = mutableStateOf(false)
    // 4. recomposition이 발생하더라도 변경 가능한 상태는 기억되고 유지돼야한다. 즉 확장 여부는 계속 변하고 기억되어야하는 정보이다.
    // 이를 위해 remember 로 랩핑하여, Compose가 이 변수를 추적하도록한다.
    //    val expanded by remember { mutableStateOf(false) }

    // 스크롤 되어도 값이 유지되도록 rememberSaveable로 변경 !
    var expanded by rememberSaveable { mutableStateOf(false) }
    // 5. Composable 함수는 이와 같은 상태를 자동으로 구독한다.
    // 6. 해당 상태가 변경되었을때 해당 함수가 재호출되는데, 이것을 recomposition 이라고 한다.
    // recomposition : 데이터가 변경되면 Compose는 새 데이터로 이러한 함수를 다시 실행하여 업데이트된 UI를 만드는 것

    // 7. by를 사용하여 .value 없이 바로 값을 사용할수 있도록 함

    // animateDpAsState를 사용하여 펼치는 애니메이션 구현
    // 애니메이션이 완료될 때까지 애니메이션에 의해 객체의 value가 계속 업데이트되는 상태 객체를 반환
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp
    )


    // MaterialTheme : 구글의 디자인 시스템
    // 따라서 배경 색(Surface의 color)만 지정해줬을 뿐이지만 텍스트의 색상도 같이 변경되는 것을 확인할 수 있음
//    Surface(
//        color = MaterialTheme.colors.background,
//        modifier = Modifier
//            .padding(vertical = 8.dp, horizontal = 16.dp)
//            .border(1.dp, MaterialTheme.colors.secondary)
//    ) {
//        Row(modifier = Modifier.padding(24.dp)) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(bottom = extraPadding)
//            ) {
//                Text(
//                    text = "Welcome :)",
//                    color = MaterialTheme.colors.secondary,
//                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Light)
//                )
//                Text(
//                    text = name, style = MaterialTheme.typography.h4.copy(
//                        fontWeight = FontWeight.Bold
//                    )
//                )
//            }
//            // Modifier : 정렬, 애니메이션 처리, 배치, 클릭 가능 여부 또는 스크롤 가능 여부 지정, 변환을 할 수 있음
//            // https://developer.android.com/jetpack/compose/modifiers-list?hl=ko
//            OutlinedButton(
//                onClick = { expanded = !expanded },
//                colors = outlinedButtonColors(MaterialTheme.colors.secondary, Color.White)
//            ) {
//                Text(if (expanded) "less" else "more", color = Color.White)
//            }
//        }
//    }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }

            )
        }

    }

}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked,
                colors = buttonColors(MaterialTheme.colors.secondary, Color.White)
            ) {
                Text("Continue")
            }
        }
    }
}

// widthDp를 세팅하여 미리보기를 변경하여 소형 스마트폰의 일반적인 너비인 320dp로 에뮬레이션한다.
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun MyApoPreview() {
    BasicsCodelabTheme {
        MyApp()
    }
}