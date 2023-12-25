package kz.amir.newsapp.data.remote.api

import kz.amir.newsapp.BuildConfig
import kz.amir.newsapp.base.constants.Constants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithAuth = chain.request().newBuilder()
            .addHeader(Constants.AUTH, BuildConfig.API_KEY)
            .build()
        return chain.proceed(requestWithAuth)
    }
}