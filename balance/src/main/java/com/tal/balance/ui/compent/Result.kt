package com.tal.balance.ui.compent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tal.balance.ui.theme.White
import com.tal.balance.ui.theme.balanceTextStyle

/**
 * 初始页面
 */
@Composable
fun Result(navigateRecommend: ()->Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // 分三块
        // 第一块 五个进度条，进度条五个颜色，浅白色底
        for (i in 1..5) {
            LinearProgressIndicator(progress = i / 5f, color = White, trackColor = Blue,)
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 第二块 综合评分结果，A+或者A、B
        Text(text = "A+", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(600), fontFamily = balanceTextStyle, fontSize = 60.sp)

        Spacer(modifier = Modifier.height(16.dp))
        // 第三块 总结文本，背景浅白色底
        Card(elevation = CardDefaults.cardElevation(5.dp)) {
            Text(text = "总结文本", modifier = Modifier.padding(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 底下中间，黄色按钮，下一步 点击下一步推荐课程（页面切换效果）。
        Button(onClick = {
            navigateRecommend()
        }) {
            Text(text = "下一步")
        }
    }
}