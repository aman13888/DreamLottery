package com.dream.lottery


import com.dream.lottery.data.LotteryDraw

object TestData {

    val lotteryDraw = LotteryDraw("draw-1", "2023-05-15", "2", "16", "23", "44", "47", "52", "14", 4000000000L)
    val lotteryDrawsList = listOf(
        lotteryDraw
    )
}
