package kz.amir.newsapp.data.remote.service

import kz.amir.newsapp.data.remote.model.NewsData
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=us")
    suspend fun getNews(): Response<NewsData>
}