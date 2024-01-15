package kz.amir.newsapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.amir.newsapp.base.util.MapTo
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.model.Source

@Parcelize
data class NewsUI(
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val isSaved: Boolean = false
) : Parcelable, MapTo<Article> {
    override fun mapTo(): Article = Article(
        source = Source(id = null, name = sourceName),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}