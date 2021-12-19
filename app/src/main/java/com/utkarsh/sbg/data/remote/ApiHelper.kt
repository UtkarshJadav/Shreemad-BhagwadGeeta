package com.utkarsh.sbg.data.remote

import com.utkarsh.sbg.utils.common.Unauthorized
import com.utkarsh.sbg.utils.extention.fromJson
import com.utkarsh.sbg.utils.common.ErrorModel
import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import java.net.UnknownHostException

inline fun <T> executeApiHelper(responseMethod: () -> Response<T>): ApiResponse<T> {
    return try {
        val response = responseMethod.invoke()
        when (response.code()) {
            in 200..300 -> {
                val responseBody = response.body()
                if (responseBody != null) {
                    ApiResponse.Success(responseBody)
                } else ApiResponse.ServerError("The application has encountered an unknown error.")
            }
            400 -> ApiResponse.ServerError("Invalid syntax for this request was provided.")
            401 -> {
                EventBus.getDefault().post(Unauthorized)
                ApiResponse.UnauthorizedAccess("You are unauthorized to access the requested resource. Please log in.")
            }
            404 -> ApiResponse.ServerError("We could not find the resource you requested. Please refer to the documentation for the list of resources.")
            422 -> {
                val errorMessage =
                    response.errorBody()?.string()?.fromJson<ErrorModel>()?.error?.message
                ApiResponse.ApiError(errorMessage ?: "Unexpected internal server error.")
            }
            500 -> ApiResponse.ServerError("Unexpected internal server error.")
            else -> ApiResponse.ServerError("Unexpected internal server error.")
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        when (exception) {
            is UnknownHostException -> ApiResponse.NoInternetConnection
            else -> ApiResponse.ServerError("Unexpected internal server error.")
        }
    }
}
