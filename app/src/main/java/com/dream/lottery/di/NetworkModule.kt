package com.dream.lottery.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.dream.lottery.utils.AppConstants.BASE_URL
import com.dream.lottery.data.LotteryApiService
import com.dream.lottery.data.LotteryDB
import com.dream.lottery.data.LotteryDrawDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLotteryDatabase(@ApplicationContext context: Context): LotteryDB {
        return Room.databaseBuilder(
            context,
            LotteryDB::class.java,
            "lottery.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLotteryDao(database: LotteryDB): LotteryDrawDao {
        return database.lotteryDrawDao()
    }

    @Provides
    @Singleton
    fun provideInstantLotteryApiService(retrofit: Retrofit): LotteryApiService {
        return retrofit.create(LotteryApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("OkHttp", message)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}