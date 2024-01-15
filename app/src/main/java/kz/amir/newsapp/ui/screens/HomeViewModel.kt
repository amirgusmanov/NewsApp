package kz.amir.newsapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kz.amir.newsapp.domain.repository.NewsRepository
import kz.amir.newsapp.ui.model.NewsUI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        getNews()
    }

    fun getSavedNews() {
        viewModelScope.launch {
            newsRepository.getSavedNews()
                .flowOn(Dispatchers.IO)
                .onStart { _state.value = State.ShowLoading }
                .catch { _state.value = State.Error(it) }
                .collectLatest {
                    _state.value = State.Success(it.map { article -> article.mapTo() })
                }
        }
    }

    fun getNews(category: String? = null) {
        viewModelScope.launch {
            newsRepository.getNews(category)
                .onStart { _state.value = State.ShowLoading }
                .flowOn(Dispatchers.IO)
                .catch { _state.value = State.Error(it) }
                .collectLatest { news ->
                    val articles = news.articles?.map { article ->
                        article.mapTo().copy(
                            isSaved = newsRepository.containsArticleWithTitle(
                                article.title.toString()
                            )
                        )
                    }
                    _state.value = State.Success(articles)
                }
        }
    }

    sealed interface State {
        data object ShowLoading : State
        data class Success(val data: List<NewsUI>?) : State
        data class Error(val error: Throwable) : State
    }
}