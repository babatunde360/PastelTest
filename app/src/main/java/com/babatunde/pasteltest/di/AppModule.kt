package com.babatunde.pasteltest.di

import android.content.Context
import androidx.room.Room
import com.babatunde.pasteltest.network.NewsService
import com.babatunde.pasteltest.repository.NewsRepository
import com.babatunde.pasteltest.repository.NewsRepositoryImpl
import com.babatunde.pasteltest.room.NewsDao
import com.babatunde.pasteltest.room.NewsDatabase
import com.babatunde.pasteltest.utils.Constants.BASE_URL
import com.babatunde.pasteltest.utils.Constants.CONNECTIVITY_TIMEOUT
import com.babatunde.pasteltest.utils.NetworkConnectivityObserver
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun provideGson(): Gson {
        return Gson().newBuilder().setLenient().create()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(provideLoggingInterceptor())
            .callTimeout(CONNECTIVITY_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTIVITY_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideApiService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideClient())
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NewsDatabase {
        return Room.databaseBuilder(
            appContext,
            NewsDatabase::class.java,
            NewsDatabase.db_Name
        ).build()
    }

    @InstallIn(SingletonComponent::class)
    @Module
    class DatabaseModule {
        @Provides
        fun provideChannelDao(appDatabase: NewsDatabase): NewsDao {
            return appDatabase.dbNewsDao()
        }
    }
    @InstallIn(SingletonComponent::class)
    @Module
    class RepositoryModule{
        @Provides
        fun providesRepository(service:NewsService,
                               database: NewsDao) =
                NewsRepositoryImpl(service,database) as
                        NewsRepository

    }

    @InstallIn(SingletonComponent::class)
    @Module
    class NetworkObserverModule {
        @Provides
        fun networkObserver(@ApplicationContext appContext: Context): NetworkConnectivityObserver {
            return NetworkConnectivityObserver(appContext)
        }
    }

}
