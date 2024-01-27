package com.tal.balance.ui.compent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tal.balance.ui.theme.AnalyzeTheme
import com.tal.balance.ui.theme.BackGroundColor
import com.tal.balance.ui.theme.White
import com.tal.balance.ui.theme.White_50
import com.tal.balance.ui.theme.balanceTextStyle

/**
 * 初始页面
 */
@Composable
fun Result(navigateRecommend: () -> Unit, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        val (row, button) = createRefs()
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(row) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            // 第一块 五个进度条，进度条五个颜色，浅白色底
            Column {
                for (i in 1..5) {
                    LinearProgressIndicator(
                        modifier = Modifier.height(10.dp),
                        progress = i / 5f,
                        color = White,
                        trackColor = White_50
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
            // 第二块 综合评分结果，A+或者A、B
            Text(
                text = "A+",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(600),
                fontFamily = balanceTextStyle,
                fontSize = 80.sp
            )
            // 第三块 总结文本，背景浅白色底
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(White_50)) {
                Text(text = "总结".trimMargin(), modifier = Modifier.padding(16.dp))
                Text(text = """|你是一个机智的孩子
                    |在做事情时有足够的专注力
                    |能够抵抗外界的干扰
                    |如果你可以适当的增加记忆力
                    |在学习方面会有很大的进展
                """.trimMargin(),letterSpacing = 2.sp, modifier = Modifier.padding(16.dp))
            }
        }
        // 底下中间，黄色按钮，下一步 点击下一步推荐课程（页面切换效果）。
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff9c05b)),
            onClick = {
            navigateRecommend()
        }, modifier = Modifier.constrainAs(button) {
            top.linkTo(row.bottom, margin = 30.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = "下一步")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun Preview() {
    AnalyzeTheme {
        Result({})
    }
}
