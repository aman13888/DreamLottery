package com.dream.lottery.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dream.lottery.data.LotteryDraw
import com.dream.lottery.data.LotteryTicket

@Composable
fun LotteryDrawScreen(draw: LotteryDraw, ticket: List<LotteryTicket>) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LotteryDrawDetailScreen(draw)
            LotteryTicketsScreen(ticket)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        val draw = LotteryDraw(
            id = "draw-1",
            drawDate = "2023-05-15",
            number1 = "2",
            number2 = "16",
            number3 = "23",
            number4 = "44",
            number5 = "47",
            number6 = "52",
            bonusBall = "14",
            topPrize = 4000000000
        )
        Column {
            val lotteryTicket1 = LotteryTicket(
                numbers = listOf(2, 16, 23, 44, 47, 52),
                isWinner = true
            )
            val lotteryTicket2 = LotteryTicket(
                numbers = listOf(21, 6, 22, 46, 41, 55),
                isWinner = false
            )
            LotteryDrawDetailScreen(draw)
            LotteryTicketsScreen(listOf(lotteryTicket1, lotteryTicket2))
        }
    }
}