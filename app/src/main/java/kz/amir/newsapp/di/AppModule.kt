package kz.amir.newsapp.di

import kz.amir.newsapp.data.remote.api.NewsApiClient
import kz.amir.newsapp.data.repository.NewsRepositoryImpl
import kz.amir.newsapp.domain.repository.NewsRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single {
        NewsRepositoryImpl(newsApi = NewsApiClient.newsService())
    } bind NewsRepository::class
}