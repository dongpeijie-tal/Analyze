package com.tal.balance.ui.custom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.tal.balance.ui.theme.BackGroundColor
import com.tal.balance.ui.theme.ProgressBackColor
import com.tal.balance.ui.theme.ProgressEndColor
import com.tal.balance.ui.theme.ProgressStartColor
import java.lang.Float.min
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun PentagonChart(modifier: Modifier,values: List<Float>) {
    val path = remember { Path() }
    val backgroundPath = remember { Path() }
    val angle = 2 * PI / values.size
    Canvas(modifier) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = min(center.x, center.y)
        // Draw background
        for (i in values.indices) {
            val x = center.x + radius * cos(i * angle - PI / 2).toFloat()
            val y = center.y + radius * sin(i * angle - PI / 2).toFloat()
            if (i == 0) {
                backgroundPath.moveTo(x, y)
            } else {
                backgroundPath.lineTo(x, y)
            }
        }
        backgroundPath.close()
        drawPath(backgroundPath, ProgressBackColor)
        // Draw chart
        values.forEachIndexed { index, value ->
            val x = center.x + radius * value * cos(index * angle - PI / 2).toFloat()
            val y = center.y + radius * value * sin(index * angle - PI / 2).toFloat()
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        path.close()
        drawPath(path, ProgressEndColor)
    }
}