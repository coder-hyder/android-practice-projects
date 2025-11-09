package com.example.newsapp.data.di

import com.example.newsapp.common.BASE_URL
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repoimp.RepoImpl
import com.example.newsapp.domain.repo.Repo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepoImpl(newsApi: NewsApi): Repo{
        return RepoImpl(newsApi = newsApi)
    }
}