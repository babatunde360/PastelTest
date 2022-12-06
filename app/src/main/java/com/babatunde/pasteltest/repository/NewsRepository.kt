package com.babatunde.pasteltest.repository

import com.babatunde.pasteltest.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun loadNewsFromNetwork()
    fun getNews(): Flow<List<Article>>
}