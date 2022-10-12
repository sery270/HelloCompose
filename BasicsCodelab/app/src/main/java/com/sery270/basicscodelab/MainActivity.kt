package com.sery270.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sery270.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme { // setContent 내에서 사용되는 앱 테마는 프로젝트 이름에 맞게 지정됩니다.
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
private fun myApp(names: List<String>) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {

    // 1. 각 아이템 (greeting) 마다 확장 여부 정보가 필요하다. 논리적으로 expanded 정보는 각 아이템이 가지고 있어야한다.
    // 2. Boolean 같은 자료형으로 상태를 저장하면 안된다.
    // var expanded = false
    // 3. Composable에 내부 상태를 저장하기 위해서 mutableStateOf 를 사용해야한다.
    // val expanded = mutableStateOf(false)
    // 4. recomposition이 발생하더라도 변경 가능한 상태는 기억되고 유지돼야한다. 즉 확장 여부는 계속 변하고 기억되어야하는 정보이다.
    // 이를 위해 remember 로 랩핑하여, Compose가 이 변수를 추적하도록한다.
    val expanded = remember { mutableStateOf(false) }
    // 5. Composable 함수는 이와 같은 상태를 자동으로 구독한다.
    // 6. 해당 상태가 변경되었을때 해당 함수가 재호출되는데, 이것을 recomposition 이라고 한다.
    // recomposition : 데이터가 변경되면 Compose는 새 데이터로 이러한 함수를 다시 실행하여 업데이트된 UI를 만드는 것


    // MaterialTheme : 구글의 디자인 시스템
    // 따라서 배경 색(Surface의 color)만 지정해줬을 뿐이지만 텍스트의 색상도 같이 변경되는 것을 확인할 수 있음
    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Welcome :)")
                Text(text = name)
            }
            // Modifier : 정렬, 애니메이션 처리, 배치, 클릭 가능 여부 또는 스크롤 가능 여부 지정, 변환을 할 수 있음
            // https://developer.android.com/jetpack/compose/modifiers-list?hl=ko
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "less" else "more")
            }
        }
    }
}

// widthDp를 세팅하여 미리보기를 변경하여 소형 스마트폰의 일반적인 너비인 320dp로 에뮬레이션한다.
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
    BasicsCodelabTheme {
        myApp(listOf("sery", "270", "kakaopay"))
    }
}