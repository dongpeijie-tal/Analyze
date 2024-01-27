package com.tal.balance

import android.os.Build.DEVICE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.didi.drouter.annotation.Router
import com.tal.balance.ui.BalanceViewModel
import com.tal.balance.ui.compent.Recommend
import com.tal.balance.ui.compent.Result
import com.tal.balance.ui.theme.AnalyzeTheme
import com.tal.balance.ui.theme.White
import com.tal.balance.ui.theme.balanceTextStyle
import com.tal.common.Route.Companion.ROUTE_BALANCE

@Router(path = ROUTE_BALANCE)
class BalanceActivity : ComponentActivity() {

    private val vm by viewModels<BalanceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnalyzeTheme {
                Detail()
            }
        }
    }

    @Composable
    fun Detail() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row (modifier = Modifier.height(IntrinsicSize.Min), horizontalArrangement = Arrangement.Start){
                    Image(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "",
                        modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { finishAfterTransition() })
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center){
                        Text(
                            text = "测试结果",
                            color = White,
                            fontFamily = balanceTextStyle,
                            fontSize = 34.sp
                        )
                    }
                }
                Body()
            }
        }
    }

    @Composable
    fun Body(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val graph = navController.createGraph(startDestination = page_init) {
            composable(page_init) {
                Result(navigateRecommend = {
                    navController.navigate(
                        page_recommend
                    )
                })
            }
            composable(page_recommend) { Recommend() }
        }
        NavHost(navController = navController, graph = graph)
    }

    @Preview(showBackground = true, showSystemUi = true, device = Devices.TABLET)
    @Composable
    fun Preview() {
        AnalyzeTheme {
            Detail()
        }
    }

    companion object {
        const val page_init = "init"
        const val page_recommend = "recommend"
    }
}

