package com.tal.balance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.didi.drouter.annotation.Router
import com.tal.balance.ui.BalanceViewModel
import com.tal.balance.ui.compent.Recommend
import com.tal.balance.ui.compent.Result
import com.tal.balance.ui.theme.AnalyzeTheme
import com.tal.common.Route.Companion.ROUTE_BALANCE

@Router(path= ROUTE_BALANCE)
class BalanceActivity : ComponentActivity() {

    private val vm by viewModels<BalanceViewModel>()

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

    @Composable
    fun Body(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val graph = navController.createGraph(startDestination = page_init){
            composable(page_init){Result(navigateRecommend = {navController.navigate(page_recommend)})}
            composable(page_recommend){ Recommend() }
        }
        NavHost(navController = navController, graph = graph)
    }

    @Preview(showBackground = true,showSystemUi = true)
    @Composable
    fun Preview() {
        AnalyzeTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Body()
            }
        }
    }

    companion object{
        const val page_init = "init"
        const val page_recommend = "recommend"
    }
}

