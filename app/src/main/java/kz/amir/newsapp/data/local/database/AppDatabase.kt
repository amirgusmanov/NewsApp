package kz.amir.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.amir.newsapp.data.local.dao.NewsDao
import kz.amir.newsapp.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}