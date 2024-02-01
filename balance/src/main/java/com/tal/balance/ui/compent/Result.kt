package com.tal.balance.ui.compent

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tal.balance.ui.custom.GradientProgressIndicator
import com.tal.balance.ui.custom.PentagonChart
import com.tal.balance.ui.theme.AnalyzeTheme
import com.tal.balance.ui.theme.BackGroundColor
import com.tal.balance.ui.theme.ProgressEndColor
import com.tal.balance.ui.theme.ProgressStartColor
import com.tal.balance.ui.theme.TextColor
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
            .padding(10.dp)
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
            Column (modifier = Modifier.width(200.dp)){
                for (i in 1..5) {
                    GradientProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        progress = i / 5f,
                        gradient = Brush.horizontalGradient(
                            colors = listOf(ProgressStartColor, ProgressEndColor),
                            startX = 0f,
                            endX = Float.POSITIVE_INFINITY
                        )
                    )
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
            // 第二块 综合评分结果，A+或者A、B
            AnimatedBox()
//            Box {
//                PentagonChart(modifier = Modifier.size(400.dp),listOf(0.2f,0.4f,0.6f,0.8f,1f))
//                Text(
//                    text = "A+",
//                    modifier = Modifier.align(Alignment.Center),
//                    color = TextColor,
//                    style = MaterialTheme.typography.titleLarge,
//                    fontWeight = FontWeight(600),
//                    fontFamily = balanceTextStyle,
//                    fontSize = 80.sp
//                )
//            }
            // 第三块 总结文本，背景浅白色底
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(White_50)) {
                Text(text = """|你是一个机智的孩子
                    |在做事情时有足够的专注力
                    |能够过滤大量的外界的干扰
                    |如果你可以适当的增加记忆力
                    |在学习方面会有很大的进展
                """.trimMargin(),color = TextColor,letterSpacing = 2.sp, modifier = Modifier.padding(16.dp))
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


@Composable
fun AnimatedBox() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotationY = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 180f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    val delay = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, delayMillis = 1000),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box {
        PentagonChart(
            modifier = Modifier
                .size(400.dp)
                .graphicsLayer(rotationY = if (delay.value < 0.5f) rotationY.value else 180f),
            listOf(0.2f, 0.4f, 0.6f, 0.8f, 1f)
        )
        Text(
            text = "A+",
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(rotationY = if (delay.value < 0.5f) rotationY.value else 180f),
            color = TextColor,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(600),
            fontFamily = balanceTextStyle,
            fontSize = 80.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
@Composable
fun Preview() {
    AnalyzeTheme {
        Result({})
    }
}
