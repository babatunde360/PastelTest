package com.babatunde.pasteltest.model

data class BaseResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)