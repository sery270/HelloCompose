package com.sery270.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    // MaterialTheme : 구글의 디자인 시스템
    // 따라서 배경 색(Surface의 color)만 지정해줬을 뿐이지만 텍스트의 색상도 같이 변경되는 것을 확인할 수 있음
    Surface(color = MaterialTheme.colors.secondary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Text(text = "Welcome :)")
            Text(text = name)
        }
        // Modifier : 정렬, 애니메이션 처리, 배치, 클릭 가능 여부 또는 스크롤 가능 여부 지정, 변환을 할 수 있음
        // https://developer.android.com/jetpack/compose/modifiers-list?hl=ko
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