package com.dream.lottery.data

import com.dream.lottery.utils.DataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LotteryRepository @Inject constructor(
    private val lotteryDrawDao: LotteryDrawDao,
    private val remoteDataSource: LotteryApiService,
) : ILotteryRepository {

    override suspend fun getLotteryList(): Flow<DataResponse<List<LotteryDraw>>> {
        return flow {
            // api handle Initialize
            emit(DataResponse.Loading)
            try {
                val response =
                    remoteDataSource.getLotteryList()
                if (response.isSuccessful) { // api Success case
                    response.body()?.let { lotteryData ->
                        if (lotteryData.draws.isNullOrEmpty()) throw Exception("Draws List Empty")
                        lotteryDrawDao.insertDraws(lotteryData.draws) // save to local db
                        emit(DataResponse.Success(lotteryData.draws)) // api Success valid body case
                    } ?: run {
                        showLocalCache()
                    }
                } else {
                    showLocalCache()  // api call fail case
                }
            } catch (ex: Exception) {
                showLocalCache()  // network fail or parse exemption
            }
        }
    }

    private suspend fun FlowCollector<DataResponse<List<LotteryDraw>>>.showLocalCache() {
        if (lotteryDrawDao.getAllDraws().isNullOrEmpty()) {
            emit(DataResponse.Error("Error"))  // api Success  body empty case
        } else {
            emit(DataResponse.Success(lotteryDrawDao.getAllDraws())) // local cache
        }
    }
}