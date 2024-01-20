package kz.amir.newsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.model.News
import kz.amir.newsapp.domain.model.SearchHistory

interface NewsRepository {
    suspend fun insertArticle(article: Article): Boolean
    suspend fun loadArticleByTitle(title: String): Flow<Article>
    suspend fun deleteArticleByTitle(title: String)
    suspend fun clearArticles()
    suspend fun getSavedNews(): Flow<List<Article>>
    suspend fun getNews(category: String?): Flow<News>
    suspend fun getNewsBySearch(keyword: String): Flow<News>
    suspend fun getSearchHistory(): Flow<List<SearchHistory>>
    suspend fun containsArticleWithTitle(title: String): Boolean
}