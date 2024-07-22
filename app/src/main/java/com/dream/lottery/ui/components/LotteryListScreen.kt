package com.dream.lottery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dream.lottery.utils.DataResponse
import com.dream.lottery.data.LotteryDraw

@Composable
fun LotteryListScreen(
    lotteryDraws: DataResponse<List<LotteryDraw>>,
    onLotteryDrawClick: (LotteryDraw) -> Unit
) {
    when (val result = lotteryDraws) {
        is DataResponse.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Mkodo Lottery",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(result.data) { draw ->
                        LotteryDrawItem(draw = draw, onClick = onLotteryDrawClick)
                    }
                }
            }
        }

        is DataResponse.Error -> {
            ShowError()
        }

        DataResponse.Loading -> {
            ShowLoader()
        }
    }
}

@Composable
private fun ShowError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: Please try again latter",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
private fun ShowLoader() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun LotteryDrawItem(draw: LotteryDraw, onClick: (LotteryDraw) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(draw) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick(draw) }
        ) {
            Text(text = "Draw ID: ${draw.id}")
            Text(text = "Draw Date: ${draw.drawDate}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLoaderPreview() {
    ShowLoader()
}

@Preview(showBackground = true)
@Composable
fun ShowErrorPreview() {
    ShowError()
}

@Preview(showBackground = true)
@Composable
fun LotteryListScreenPreview() {
    LotteryListScreen(
        lotteryDraws = DataResponse.Success(
            listOf(
                LotteryDraw(
                    id = "draw-1",
                    drawDate = "2023-05-15",
                    number1 = "2",
                    number2 = "16",
                    number3 = "23",
                    number4 = "44",
                    number5 = "45",
                    number6 = "46",
                    bonusBall = "47",
                    topPrize = 1000000
                )
            )
        ),
        onLotteryDrawClick = {}
    )
}