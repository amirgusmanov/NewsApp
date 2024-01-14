package kz.amir.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.amir.newsapp.base.util.MapTo
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.model.Source

@Entity
data class NewsEntity(
    @ColumnInfo(name = "sourceName") val sourceName: String?,
    @ColumnInfo(name = "author") val author: String?,
    @PrimaryKey @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "urlToImage") val urlToImage: String?,
    @ColumnInfo(name = "publishedAt") val publishedAt: String?,
    @ColumnInfo(name = "content") val content: String?
) : MapTo<Article> {
    override fun mapTo(): Article = Article(
        source = Source(name = this.sourceName, id = null),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}