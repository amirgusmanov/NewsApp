package kz.amir.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.amir.newsapp.base.util.MapTo
import kz.amir.newsapp.domain.model.SearchHistory
import java.time.OffsetDateTime

@Entity
data class SearchHistoryEntity(
    @PrimaryKey @ColumnInfo("title") val title: String,
    @ColumnInfo("imageUrl") val imageUrl: String?,
    @ColumnInfo("joined_date") val joinedDate: OffsetDateTime? = null
) : MapTo<SearchHistory> {
    override fun mapTo(): SearchHistory = SearchHistory(
        title = this.title,
        imageUrl = this.imageUrl
    )
}
