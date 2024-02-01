package com.tal.balance.ui.custom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tal.balance.ui.theme.ProgressBackColor
import com.tal.balance.ui.theme.ProgressEndColor
import com.tal.balance.ui.theme.ProgressStartColor

@Composable
fun GradientProgressIndicator(
    progress: Float,
    gradient: Brush,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .height(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(ProgressBackColor)
    ) {
        Box(
            Modifier
                .fillMaxWidth(progress)
                .height(10.dp)
                .background(brush = gradient)
        )
    }
}

@Preview
@Composable
fun PreviewGradientProgressIndicator() {
    val gradient = Brush.horizontalGradient(
        colors = listOf(ProgressStartColor, ProgressEndColor),
        startX = 0f,
        endX = Float.POSITIVE_INFINITY
    )
    GradientProgressIndicator(progress = 0.5f, gradient = gradient)
}