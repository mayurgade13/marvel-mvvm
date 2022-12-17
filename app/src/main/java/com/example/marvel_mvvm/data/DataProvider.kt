package com.example.marvel_mvvm.data

import com.example.marvel_mvvm.common.Resource
import com.example.marvel_mvvm.data.remote.dto.ErrorResponse
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class DataProvider {

    suspend fun <T> getApiResponse(apiToBeCalled: suspend() -> Response<T>): Resource<T> {
        return try {
            val response = apiToBeCalled.invoke()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())
                Resource.Error(errorMessage = errorResponse?.message ?: "Something went wrong")
            }
        } catch (e: IOException) {
            Resource.Error("No Internet Connect")
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "Something went wrong")
        } catch (e: Exception) {
            Resource.Error("Something went wrong")
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}
