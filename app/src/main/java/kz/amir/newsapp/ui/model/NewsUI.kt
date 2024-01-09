package kz.amir.newsapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class NewsUI(
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) : Parcelable