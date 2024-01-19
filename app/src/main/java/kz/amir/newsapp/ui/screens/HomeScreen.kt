package kz.amir.newsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kz.amir.newsapp.base.constants.Categories
import kz.amir.newsapp.base.constants.Constants
import kz.amir.newsapp.ui.components.CategoriesList
import kz.amir.newsapp.ui.components.Error
import kz.amir.newsapp.ui.components.Loading
import kz.amir.newsapp.ui.components.NewsList
import kz.amir.newsapp.ui.components.SearchInput
import kz.amir.newsapp.ui.navigation.Screen

/**
 * todo: think about getting locale country code and saving it locally
 * todo: custom or lib pagination for viewing news (Homescreen)
 * todo: think about widgets
 * val locale = LocalContext.current.resources.configuration.locales[0].country.lowercase()
 */

@Composable
fun HomeScreen(
    navController: NavController,
    needsToUpdate: Boolean?
) {
    val viewModel: HomeViewModel = viewModel()

    val uiState by viewModel.state.collectAsState()
    var isSavedCategory by rememberSaveable { mutableStateOf(false) }
    var category by rememberSaveable { mutableStateOf("") }

    needsToUpdate?.let {
        LaunchedEffect(needsToUpdate) {
            if (isSavedCategory && needsToUpdate)
                viewModel.getSavedNews()
            else if (!isSavedCategory && needsToUpdate)
                viewModel.getNews(category)
        }
    }

    Column {
        SearchInput(searchHistory = emptyList(), searchKeyword = {})

        CategoriesList(
            categories = Categories.items,
            onCategorySelected = {
                if (it == Constants.SAVED_CATEGORY) {
                    isSavedCategory = true
                    viewModel.getSavedNews()
                } else {
                    isSavedCategory = false
                    category = it
                    viewModel.getNews(it)
                }
            }
        )

        when (uiState) {
            HomeViewModel.State.ShowLoading -> Loading()
            is HomeViewModel.State.Error -> Error()
            is HomeViewModel.State.Success -> {
                NewsList(
                    news = (uiState as HomeViewModel.State.Success).data ?: emptyList(),
                    onArticleClicked = { article ->
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(key = "article", value = article)
                            if (isSavedCategory)
                                set(key = "isSaved", value = true)
                            else
                                set(key = "isSaved", value = article.isSaved)
                        }
                        navController.navigate(route = Screen.Detail.route)
                    }
                )
            }
        }
    }
}