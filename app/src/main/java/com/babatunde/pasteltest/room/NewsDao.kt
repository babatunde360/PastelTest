package com.babatunde.pasteltest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.babatunde.pasteltest.model.Article
import kotlinx.coroutines.flow.Flow

// Dao annotation to declare this class as a dao class

@Dao
interface NewsDao{

    // insert annotation to assign this function and implementation, that is only handled by roomDB
    @Insert
    suspend fun saveNews(movie: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(vararg articles:Article)


    // query annotation to specify the kind of implementation for this function
    @Query("select * from article")
    fun getMovieList(): Flow<List<Article>>

    // query annotation to specify the kind of implementation for this function
    @Query("delete from article")
    fun deleteAll()

}