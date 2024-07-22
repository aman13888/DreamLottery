package com.dream.lottery.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LotteryDrawDao {
    @Query("SELECT * FROM lottery_draws")
    suspend fun getAllDraws(): List<LotteryDraw>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraws(draws: List<LotteryDraw>)
}