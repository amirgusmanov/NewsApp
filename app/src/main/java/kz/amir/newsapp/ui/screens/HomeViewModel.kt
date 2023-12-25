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
import kz.amir.newsapp.data.remote.api.NewsApiClient
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.repository.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    private val service = NewsApiClient.newsService()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            newsRepository.getNews()
                .onStart { _state.value = State.ShowLoading }
                .flowOn(Dispatchers.IO)
                .catch { _state.value = State.Error(it) }
                .collectLatest { _state.value = State.Success(it.articles) }
        }
    }

    sealed interface State {
        data object ShowLoading : State
        data class Success(val data: List<Article>?) : State
        data class Error(val error: Throwable) : State
    }
}