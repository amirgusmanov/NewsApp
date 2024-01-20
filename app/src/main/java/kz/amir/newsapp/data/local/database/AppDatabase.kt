package kz.amir.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kz.amir.newsapp.base.util.TimestampConverter
import kz.amir.newsapp.data.local.dao.NewsDao
import kz.amir.newsapp.data.local.entity.NewsEntity
import kz.amir.newsapp.data.local.entity.SearchHistoryEntity

@Database(
    entities = [NewsEntity::class, SearchHistoryEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(value = [TimestampConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}