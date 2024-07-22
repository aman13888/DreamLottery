package com.dream.lottery.ui

import androidx.lifecycle.*
import androidx.navigation.NavBackStackEntry
import com.dream.lottery.utils.DataResponse
import com.dream.lottery.data.LotteryDraw
import com.dream.lottery.data.LotteryRepository
import com.dream.lottery.data.LotteryTicket
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LotteryViewModel @Inject constructor(
    private val repository: LotteryRepository,
) : ViewModel() {

    private val _lotteryDraws =
        MutableStateFlow<DataResponse<List<LotteryDraw>>>(DataResponse.Success(emptyList()))
    val lotteryDraws: StateFlow<DataResponse<List<LotteryDraw>>> = _lotteryDraws.asStateFlow()

    init {
        fetchLotteryDraws()
    }

    fun fetchLotteryDraws() {
        viewModelScope.launch {
            repository.getLotteryList()
                .onEach { _lotteryDraws.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun generateRandomTickets(draw: LotteryDraw, ticketCount: Int = 10): List<LotteryTicket> {
        val random = Random(System.currentTimeMillis())
        val winningNumbers = listOf(
            draw.number1,
            draw.number2,
            draw.number3,
            draw.number4,
            draw.number5,
            draw.number6
        ).map { it.toInt() }
        val bonusBall = draw.bonusBall.toInt()

        return List(ticketCount) {
            val numbers = List(6) { random.nextInt(1, 60) }
            val isWinner = numbers.containsAll(winningNumbers) || numbers.contains(bonusBall)
            LotteryTicket(numbers, isWinner)
        }
    }

    fun lotteryDraw(backStackEntry: NavBackStackEntry): LotteryDraw {
        val drawJson = backStackEntry.arguments?.getString("draw")
        val draw = Gson().fromJson(drawJson, LotteryDraw::class.java)
        return draw
    }

    fun createDrawGson(draw: LotteryDraw): String {
        val drawJson = Gson().toJson(draw)
        return drawJson
    }
}
