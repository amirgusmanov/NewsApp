package kz.amir.newsapp.domain.model

import kz.amir.newsapp.base.util.MapTo
import kz.amir.newsapp.ui.model.NewsUI

data class News(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Article>?
)

data class Article(
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) : MapTo<NewsUI> {
    override fun mapTo(): NewsUI = NewsUI(
        sourceName = source?.name,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

data class Source(
    val id: String?,
    val name: String?
)