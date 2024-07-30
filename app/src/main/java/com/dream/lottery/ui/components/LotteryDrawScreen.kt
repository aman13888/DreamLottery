package com.dream.lottery.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
