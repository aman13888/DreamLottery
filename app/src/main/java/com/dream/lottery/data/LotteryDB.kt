package com.dream.lottery.data


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LotteryDraw::class], version = 1)
abstract class LotteryDB : RoomDatabase() {
    abstract fun lotteryDrawDao(): LotteryDrawDao
}