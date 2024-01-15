package kz.amir.newsapp.di

import androidx.room.Room
import kz.amir.newsapp.data.local.database.AppDatabase
import kz.amir.newsapp.data.remote.api.NewsApiClient
import kz.amir.newsapp.data.repository.NewsRepositoryImpl
import kz.amir.newsapp.domain.repository.NewsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single {
        NewsRepositoryImpl(
            newsApi = NewsApiClient.newsService(),
            db = get()
        )
    } bind NewsRepository::class

    single {
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java, "AppDatabase")
            .fallbackToDestructiveMigration()
            .build()
    } bind AppDatabase::class
}