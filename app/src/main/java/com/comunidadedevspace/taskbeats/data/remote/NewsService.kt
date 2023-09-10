package com.comunidadedevspace.taskbeats.data.remote

import com.comunidadedevspace.taskbeats.BuildConfig
import retrofit2.http.GET

//https://api.thenewsapi.com/v1/news/headlines?locale=us&language=en&api_token=kaN1lRX2LTtVvRTFtF7XWZ8vTF7qLVWBep62vWNl

interface NewsService {
    @GET("top?api_token=${BuildConfig.API_KEY}&locale=us")
    suspend fun fetchTopNews():NewsResponse

    @GET("all?api_token=${BuildConfig.API_KEY}&locale=us")
    suspend fun fetchAllNews():NewsResponse
}