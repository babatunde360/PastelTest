package com.babatunde.pasteltest.network

import com.babatunde.pasteltest.BuildConfig
import com.babatunde.pasteltest.model.BaseResponse
import com.babatunde.pasteltest.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("country") country:String = "us"
    ): BaseResponse

    //This is used for a more secure key
    @GET("top-headlines")
    suspend fun getTopHeadlinesWithSecureAPIKey(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country:String = "us"
    ): BaseResponse
}