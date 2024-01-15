package kz.amir.newsapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.amir.newsapp.domain.model.Article
import kz.amir.newsapp.domain.repository.NewsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsViewModel : ViewModel(), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    private val _state = MutableStateFlow<State>(State.ShowLoading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun deleteArticle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteArticleByTitle(title)
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = State.ShowLoading
            if (newsRepository.insertArticle(article)) {
                _state.value = State.Success
            } else {
                _state.value = State.Error("Something went wrong")
            }
        }
    }

    sealed interface State {
        data object ShowLoading : State
        data object Success : State
        data class Error(val error: String) : State
    }
}