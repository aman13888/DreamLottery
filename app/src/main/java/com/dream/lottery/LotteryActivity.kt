package com.dream.lottery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dream.lottery.ui.LotteryViewModel
import com.dream.lottery.ui.components.LotteryDrawScreen
import com.dream.lottery.ui.components.LotteryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LotteryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = hiltViewModel<LotteryViewModel>()
            MaterialTheme {
                val lotteryDraws by viewModel.lotteryDraws.collectAsState()
                NavHost(navController, startDestination = "draw_list") {
                    composable("draw_list") {
                        LotteryListScreen(
                            lotteryDraws = lotteryDraws,
                            onLotteryDrawClick = { draw ->
                                val drawJson = viewModel.createDrawGson(draw)
                                navController.navigate("draw_detail/$drawJson")
                            }
                        )
                    }
                    composable("draw_detail/{draw}") { backStackEntry ->
                        val drawDetails = viewModel.lotteryDraw(backStackEntry)
                        val randomTickets =
                            remember { viewModel.generateRandomTickets(drawDetails) }
                        LotteryDrawScreen(drawDetails, randomTickets)
                    }
                }
            }
        }
    }
}