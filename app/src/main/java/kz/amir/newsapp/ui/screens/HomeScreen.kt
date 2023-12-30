package kz.amir.newsapp.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.amir.newsapp.ui.components.CategoriesList
import kz.amir.newsapp.ui.components.Error
import kz.amir.newsapp.ui.components.Loading
import kz.amir.newsapp.ui.components.NewsList

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.state.collectAsState()

    var selectedCategory: String? by remember { mutableStateOf(null) }

    CategoriesList()

    Spacer(modifier = Modifier.height(4.dp))

    when (uiState) {
        HomeViewModel.State.ShowLoading -> Loading()
        is HomeViewModel.State.Error -> Error()
        is HomeViewModel.State.Success -> {
            NewsList(
                news = (uiState as HomeViewModel.State.Success).data ?: emptyList()
            )
        }
    }
}