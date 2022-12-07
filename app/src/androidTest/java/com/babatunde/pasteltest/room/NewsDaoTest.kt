package com.babatunde.pasteltest.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.babatunde.pasteltest.model.Article
import com.babatunde.pasteltest.model.Source
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
class NewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NewsDatabase
    private lateinit var dao:NewsDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.dbNewsDao()
    }

    @After
    fun tearDown(){
        database.close()
    }


    @Test
    fun canInsertOneNewsArticle() = runTest {

        //GIVEN
        val source = Source("cnn","CNN")
        val article = Article(
            "Anderson cooper",
         "This is a news",
       "Oil has just been discovered in the gulf ",
       "publishedAt",
       source,
       "New Dinosaur juice found",
       "https://cnn.com/dinojuicefound",
       "urlToImage"
        )
        //WHEN
        dao.insertArticle(article)
        //THEN
        val allArticles:List<Article> = dao.getMovieList().first()

        assertThat(allArticles).contains(article)
    }

    @Test
    fun canInsertMultipleNewsArticle() = runTest {

        //GIVEN
        val source = Source("cnn","CNN")
        val article = Article(
            "Anderson cooper",
         "This is a news",
       "Oil has just been discovered in the gulf ",
       "publishedAt",
       source,
       "New Dinosaur juice found",
       "https://cnn.com/dinojuicefound",
       "urlToImage"
        )

        val articleTwo = Article(
            "Anderson cooper",
         "This is a news",
       "Oil has just been discovered in the gulf ",
       "publishedAt",
       source,
       "New Dinosaur juice found",
       "https://cnn.com/dinojuicefound2",
       "urlToImage"
        )

        val articles = mutableListOf(article,articleTwo)
        val articlesArray = articles.toTypedArray()

        //WHEN
        dao.insertArticle(*articlesArray)
        //THEN
        val allArticles:List<Article> = dao.getMovieList().first()

        assertThat(allArticles).isEqualTo(articles)
    }
    @Test
    fun wouldReturnEqualNewsArticleSize() = runTest {

        //GIVEN
        val source = Source("cnn","CNN")
        val article = Article(
            "Anderson cooper",
         "This is a news",
       "Oil has just been discovered in the gulf ",
       "publishedAt",
       source,
       "New Dinosaur juice found",
       "https://cnn.com/dinojuicefound",
       "urlToImage"
        )

        val articleTwo = Article(
            "Anderson cooper",
         "This is a news",
       "Oil has just been discovered in the gulf ",
       "publishedAt",
       source,
       "New Dinosaur juice found",
       "https://cnn.com/dinojuicefound2",
       "urlToImage"
        )

        val articles = mutableListOf(article,articleTwo)
        val articlesArray = articles.toTypedArray()

        //WHEN
        dao.insertArticle(*articlesArray)
        //THEN
        val allArticles:List<Article> = dao.getMovieList().first()

        assertThat(allArticles.size).isEqualTo(articles.size)
    }

    @Test
    fun canDeleteAllNewsArticle() = runTest {

        //GIVEN
        val source = Source("cnn","CNN")
        val article = Article(
            "Anderson cooper",
            "This is a news",
            "Oil has just been discovered in the gulf ",
            "publishedAt",
            source,
            "New Dinosaur juice found",
            "https://cnn.com/dinojuicefound",
            "urlToImage"
        )

        val articleTwo = Article(
            "Anderson cooper",
            "This is a news",
            "Oil has just been discovered in the gulf ",
            "publishedAt",
            source,
            "New Dinosaur juice found",
            "https://cnn.com/dinojuicefound2",
            "urlToImage"
        )

        val articles = mutableListOf(article,articleTwo)
        val articlesArray = articles.toTypedArray()

        //WHEN
        dao.insertArticle(*articlesArray)
        //THEN
        dao.deleteAll()
        val allArticles:List<Article> = dao.getMovieList().first()

        assertThat(allArticles).isEmpty()

    }

}