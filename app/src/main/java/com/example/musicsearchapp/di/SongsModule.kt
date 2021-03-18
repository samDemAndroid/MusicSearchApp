package com.example.musicsearchapp.di

import com.example.musicsearchapp.api.MusicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SongsModule {
    private val API_KEY= "5c84c65b56d17b08392250a40583e79a"
    @Provides
    @Singleton
    fun provideMusicApiService(): MusicApiService = getClient()

    private fun getClient(): MusicApiService{
        val requestInterceptor = Interceptor { chain ->
            val url= chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("method", "track.search")
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json")
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient= OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://ws.audioscrobbler.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicApiService::class.java)

    }

}