package kz.amir.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.amir.newsapp.domain.model.News

interface NewsRepository {

    suspend fun getNews(category: String?): Flow<News>
}