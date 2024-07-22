package com.dream.lottery.data
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LotteryList(
    val draws: List<LotteryDraw>
) : Parcelable

@Parcelize
@Entity(tableName = "lottery_draws")
data class LotteryDraw(
    @PrimaryKey val id: String,
    val drawDate: String,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    @SerializedName("bonus-ball") val bonusBall: String,
    val topPrize: Long
) : Parcelable

data class LotteryTicket(
    val numbers: List<Int>,
    val isWinner: Boolean
)