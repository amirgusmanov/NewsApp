package kz.amir.newsapp.data.local.entity.mapper

import kz.amir.newsapp.data.local.entity.NewsEntity
import kz.amir.newsapp.domain.model.Article

object NewsEntityMapper {
    fun toEntity(article: Article): NewsEntity = NewsEntity(
        sourceName = article.source?.name,
        author = article.author,
        title = article.title ?: "",
        description = article.description,
        url = article.url,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content,
    )
}