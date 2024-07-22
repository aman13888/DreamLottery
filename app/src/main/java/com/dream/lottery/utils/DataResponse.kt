package com.dream.lottery.utils

sealed class DataResponse<out T> {
    data class Success<out T>(val data: T) : DataResponse<T>()  // Success
    data class Error<out T>(val message: String = "Unknown error") : DataResponse<T>()
    object Loading : DataResponse<Nothing>() // Loading data
    // Error Errors Details can be passed into it for custom UI as per Error
}
