package kz.amir.newsapp.widget.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kz.amir.newsapp.domain.repository.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class NewsWorker(
    context: Context,
    workParams: WorkerParameters
) : CoroutineWorker(context, workParams), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    companion object {
        const val WORKER_TAG = "NewsWorker"

        fun buildRequest(): PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<NewsWorker>(1, TimeUnit.MINUTES)
                .addTag(WORKER_TAG)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
    }

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }

    private suspend fun fetchNews() {
        newsRepository.getNews("")
            .flowOn(Dispatchers.IO)
            .catch { Log.e("ERROR-TAG", "fetchNews: $it") }
            .collectLatest { news ->
                val articles = news.articles?.map { article ->
                    article.mapTo().copy(
                        isSaved = newsRepository.containsArticleWithTitle(
                            article.title.toString()
                        )
                    )
                }
            }
    }
}