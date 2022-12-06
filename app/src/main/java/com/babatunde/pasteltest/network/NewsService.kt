package com.babatunde.pasteltest.network

import com.babatunde.pasteltest.BuildConfig
import com.babatunde.pasteltest.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country:String = "us"
    ): BaseResponse
}