import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dream.lottery.data.LotteryDraw
import com.dream.lottery.data.LotteryTicket
import com.dream.lottery.ui.components.LotteryDrawDetailScreen
import com.dream.lottery.ui.components.LotteryListScreen
import com.dream.lottery.ui.components.LotteryTicketsScreen
import com.dream.lottery.ui.components.ShowError
import com.dream.lottery.ui.components.ShowLoader
import com.dream.lottery.utils.DataResponse

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