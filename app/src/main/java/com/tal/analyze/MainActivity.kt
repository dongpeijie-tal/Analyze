package com.tal.analyze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tal.analyze.bugle.runBugleTest
import com.tal.analyze.ui.theme.AnalyzeTheme
import com.tal.common.Route
import com.tal.common.utils.AppRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 开发环境配置本地启动环境
        when(BuildConfig.initArg){
            "game"->{
                AppRouter.go(Route.ROUTE_GAME)
            }
            "balance"->{
                AppRouter.go(Route.ROUTE_BALANCE)
            }
        }
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
        runBugleTest()
    }
}

@Composable
fun Body(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            AppRouter.go(Route.ROUTE_BALANCE)
        }) {
            Text(text = "结算页面")
        }
        Button(onClick = {
            AppRouter.go(Route.ROUTE_GAME)
        }) {
            Text(text = "游戏页面")
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