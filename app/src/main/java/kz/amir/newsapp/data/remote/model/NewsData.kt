package kz.amir.newsapp.data.remote.model

import com.google.gson.annotations.SerializedName
import kz.amir.newsapp.base.util.MapTo
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.model.News
import kz.amir.newsapp.domain.model.Source
import java.io.Serializable

data class NewsData(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("articles") val articles: List<ArticleData>?
) : Serializable, MapTo<News> {
    override fun mapTo(): News = News(
        status = this.status,
        totalResults = this.totalResults,
        articles = this.articles?.map { it.mapTo() }
    )
}

data class ArticleData(
    @SerializedName("source") val source: Source?,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("content") val content: String?
) : Serializable, MapTo<Article> {
    override fun mapTo(): Article = Article(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

data class SourceData(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
) : Serializable, MapTo<Source> {
    override fun mapTo(): Source = Source(
        id = this.id,
        name = this.name
    )
}