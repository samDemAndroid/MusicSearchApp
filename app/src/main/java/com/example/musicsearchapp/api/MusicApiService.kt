package com.example.musicsearchapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY= "5c84c65b56d17b08392250a40583e79a"
interface MusicApiService {
    @GET("2.0")
    suspend fun getSongs(
        @Query("track") track: String?
    ): MusicResponse

    companion object {
        operator fun invoke(): MusicApiService{
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
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicApiService::class.java)

        }
    }
}