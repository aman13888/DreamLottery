package com.dream.lottery.data

import com.dream.lottery.utils.DataResponse
import kotlinx.coroutines.flow.Flow

interface ILotteryRepository {
    suspend fun getLotteryList(): Flow<DataResponse<List<LotteryDraw>>>
}