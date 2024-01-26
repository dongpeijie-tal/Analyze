package com.tal.balance.ui.compent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * 推荐页面
 */
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