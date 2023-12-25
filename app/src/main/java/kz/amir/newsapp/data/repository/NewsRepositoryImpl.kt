package kz.amir.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.amir.newsapp.data.remote.service.NewsApi
import kz.amir.newsapp.domain.model.News
import kz.amir.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override suspend fun getNews(): Flow<News> = flow {
        val response = newsApi.getNews()
        if (response.isSuccessful) {
            response.body()?.mapTo()?.let {
                emit(it)
            }
        }
    }
}