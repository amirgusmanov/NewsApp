package kz.amir.newsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.amir.newsapp.base.constants.Categories
import kz.amir.newsapp.ui.components.CategoriesList
import kz.amir.newsapp.ui.components.Error
import kz.amir.newsapp.ui.components.Loading
import kz.amir.newsapp.ui.components.NewsList

/**
 * todo: think about getting locale country code and saving it locally
 * todo: custom or lib pagination for viewing news (Homescreen)
 * todo: news preview screen
 * val locale = LocalContext.current.resources.configuration.locales[0].country.lowercase()
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.state.collectAsState()

    Column {
        CategoriesList(
            categories = Categories.items,
            onCategorySelected = { viewModel.getNews(it) }
        )

        when (uiState) {
            HomeViewModel.State.ShowLoading -> Loading()
            is HomeViewModel.State.Error -> Error()
            is HomeViewModel.State.Success -> {

                Spacer(modifier = Modifier.height(4.dp))

                NewsList(news = (uiState as HomeViewModel.State.Success).data ?: emptyList())
            }
        }
    }
}