package kz.amir.newsapp.domain.model.mapper

import kz.amir.newsapp.base.util.TimestampConverter
import kz.amir.newsapp.data.local.entity.SearchHistoryEntity
import kz.amir.newsapp.domain.model.Article

object SearchHistoryMapper {
    fun mapToEntity(title: String?, article: Article) = SearchHistoryEntity(
        title = title ?: "",
        imageUrl = article.urlToImage,
        joinedDate = TimestampConverter.toOffsetDateTime(article.publishedAt)
    )
}