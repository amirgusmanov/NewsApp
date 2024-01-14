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
import androidx.navigation.NavController
import kz.amir.newsapp.base.constants.Categories
import kz.amir.newsapp.base.constants.Constants
import kz.amir.newsapp.ui.components.CategoriesList
import kz.amir.newsapp.ui.components.Error
import kz.amir.newsapp.ui.components.Loading
import kz.amir.newsapp.ui.components.NewsList
import kz.amir.newsapp.ui.navigation.Screen

/**
 * todo: think about getting locale country code and saving it locally
 * todo: custom or lib pagination for viewing news (Homescreen)
 * val locale = LocalContext.current.resources.configuration.locales[0].country.lowercase()
 */

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()

    val uiState by viewModel.state.collectAsState()
//    todo: think about validation before opening details screen
//    var isSaved by rememberSaveable { mutableStateOf(false) }

    Column {
        CategoriesList(
            categories = Categories.items,
            onCategorySelected = {
                if (it == Constants.SAVED_CATEGORY) {
//                    isSaved = true
                    viewModel.getSavedNews()
                } else {
//                    isSaved = false
                    viewModel.getNews(it)
                }
            }
        )

        when (uiState) {
            HomeViewModel.State.ShowLoading -> Loading()
            is HomeViewModel.State.Error -> Error()
            is HomeViewModel.State.Success -> {

                Spacer(modifier = Modifier.height(4.dp))

                NewsList(
                    news = (uiState as HomeViewModel.State.Success).data ?: emptyList(),
                    onArticleClicked = { article ->
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(key = "article", value = article)
//                            set(key = "isSaved", value = viewModel.isArticleSaved)
                        }
                        navController.navigate(route = Screen.Detail.route)
                    }
                )
            }
        }
    }
}