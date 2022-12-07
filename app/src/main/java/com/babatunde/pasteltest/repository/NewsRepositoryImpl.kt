package com.babatunde.pasteltest.repository

import android.util.Log
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import com.babatunde.pasteltest.model.Article
import com.babatunde.pasteltest.network.NewsService
import com.babatunde.pasteltest.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(private var service:NewsService,
                                             private val database: NewsDao
):NewsRepository {
    override suspend fun loadNewsFromNetwork(){
        try {
            val result = service.getTopHeadlines()
            if (result.status.toLowerCase(Locale.current) == "ok"){
                database.deleteAll()
                database.insertArticle(*result.articles.toTypedArray())
            }
        }catch (e:Exception){
            Log.d(TAG, "loadNews: An error occurred")
        }
    }

    override fun getNews(): Flow<List<Article>> {
       return database.getMovieList()
    }

    companion object{
        private const val TAG = "NewsRepository"
    }
}