package kz.amir.newsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.amir.newsapp.data.local.database.AppDatabase
import kz.amir.newsapp.data.local.entity.mapper.NewsEntityMapper
import kz.amir.newsapp.data.remote.service.NewsApi
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.model.News
import kz.amir.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val db: AppDatabase
) : NewsRepository {
    override suspend fun insertArticle(article: Article): Boolean =
        db.newsDao().insertArticle(NewsEntityMapper.toEntity(article)).isNotEmpty()

    override suspend fun loadArticleByTitle(title: String): Flow<Article> = flow {
        val article = db.newsDao().loadArticleByTitle(articleTitle = title).mapTo()
        emit(article)
    }

    override suspend fun deleteArticleByTitle(title: String) {
        db.newsDao().deleteArticleByTitle(articleTitle = title)
    }

    override suspend fun clearArticles() {
        db.newsDao().clearArticles()
    }

    override suspend fun getSavedNews(): Flow<List<Article>> = flow {
        val news = db.newsDao().getAll().map { entity -> entity.mapTo() }
        emit(news)
    }

    override suspend fun getNews(category: String?): Flow<News> = flow {
        val response = newsApi.getNews(category ?: "")
        if (response.isSuccessful) {
            response.body()?.mapTo()?.let {
                emit(it)
            }
        }
    }

    override suspend fun containsArticleWithTitle(title: String): Boolean =
        db.newsDao().containsElementWithTitle(title)
}