package com.tal.balance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.didi.drouter.annotation.Router
import com.tal.balance.ui.theme.AnalyzeTheme
import com.tal.balance.ui.theme.White
import com.tal.common.Route.Companion.ROUTE_BALANCE

@Router(path= ROUTE_BALANCE)
class BalanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnalyzeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // title 结算标题->测试结果
        Text(text = "测试结果", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        // 分三块
        // 第一块 五个进度条，进度条五个颜色，浅白色底
        for (i in 1..5) {
            LinearProgressIndicator(progress = i / 5f, color = White)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 第二块 综合评分结果，A+或者A、B
        Text(text = "A+", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
        // 第三块 总结文本，背景浅白色底
        Card(elevation = CardDefaults.cardElevation(5.dp)) {
            Text(text = "总结文本", modifier = Modifier.padding(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 底下中间，黄色按钮，下一步 点击下一步推荐课程（页面切换效果）。
        Button(onClick = {

        }) {
            Text(text = "下一步")
        }
    }
}

@Composable
fun Recommend(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "建议课程", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // 课程推荐
        for (i in 1..3) {
            Text(text = "课程$i: 原因")
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 底部按钮
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {

            }) {
                Text(text = "再看看")
            }
            Button(onClick = {

            }) {
                Text(text = "进入课程详情")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnalyzeTheme {
        Body()
    }
}