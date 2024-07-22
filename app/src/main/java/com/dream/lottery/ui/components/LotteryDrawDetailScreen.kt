package com.dream.lottery.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dream.lottery.data.LotteryDraw

@Composable
fun LotteryDrawDetailScreen(draw: LotteryDraw) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Lottery Draw Details",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    DrawDetailRow(label = "Draw ID", value = draw.id)
    DrawDetailRow(label = "Draw Date", value = draw.drawDate)
    DrawDetailRow(label = "Number 1", value = draw.number1)
    DrawDetailRow(label = "Number 2", value = draw.number2)
    DrawDetailRow(label = "Number 3", value = draw.number3)
    DrawDetailRow(label = "Number 4", value = draw.number4)
    DrawDetailRow(label = "Number 5", value = draw.number5)
    DrawDetailRow(label = "Number 6", value = draw.number6)
    DrawDetailRow(label = "Bonus Ball", value = draw.bonusBall)
    DrawDetailRow(label = "Top Prize", value = draw.topPrize.toString())
}

@Composable
fun DrawDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            fontSize = 18.sp
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LotteryDrawDetailScreenPreview() {
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
    LotteryDrawDetailScreen(draw)
}
