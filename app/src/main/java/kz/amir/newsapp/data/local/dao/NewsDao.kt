package kz.amir.newsapp.data.local.dao

import android.util.Log
import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kz.amir.newsapp.data.local.entity.NewsEntity

@Keep
@Dao
interface NewsDao {
    @Query("SELECT * FROM NewsEntity")
    suspend fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(vararg articles: NewsEntity): List<Long>

    @Query("SELECT * FROM NewsEntity WHERE title = (:articleTitle)")
    suspend fun loadArticleByTitle(articleTitle: String): NewsEntity

    @Query("DELETE FROM NewsEntity WHERE title = (:articleTitle)")
    suspend fun deleteArticleByTitle(articleTitle: String)

    @Query("DELETE FROM NewsEntity")
    suspend fun clearArticles()

    @Query("SELECT * FROM NewsEntity WHERE title = :title")
    suspend fun getNewsWithTitle(title: String): List<NewsEntity>

    @Transaction
    suspend fun containsElementWithTitle(title: String): Boolean {
        val elements = getNewsWithTitle(title)
        Log.d("SAVEDTAG", "containsElementWithTitle: Checking")
        return elements.isNotEmpty()
    }
}