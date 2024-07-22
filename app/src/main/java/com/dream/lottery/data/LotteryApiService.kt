package com.dream.lottery.data

import retrofit2.Response
import retrofit2.http.GET

interface LotteryApiService {

    @GET("sample")
    suspend fun getLotteryList(): Response<LotteryList>

}
