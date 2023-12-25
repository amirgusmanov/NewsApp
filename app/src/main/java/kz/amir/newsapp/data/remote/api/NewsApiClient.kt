package kz.amir.newsapp.data.remote.api

import kz.amir.newsapp.BuildConfig
import kz.amir.newsapp.base.constants.Constants
import kz.amir.newsapp.data.remote.service.NewsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NewsApiClient {

    private val okHttp = OkHttpClient.Builder()
        .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(AuthInterceptor())
        .also {
            if (BuildConfig.DEBUG) it.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun newsService(): NewsApi = retrofit.create(NewsApi::class.java)
}