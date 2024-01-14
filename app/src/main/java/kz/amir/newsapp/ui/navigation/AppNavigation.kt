package kz.amir.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.amir.newsapp.ui.model.NewsUI
import kz.amir.newsapp.ui.screens.DetailsScreen
import kz.amir.newsapp.ui.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Detail.route) {
            /**
             * corner case: when navigate to home if you unsaved article it needs to be removed
             * from home screen list
             */
            navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.apply {
                    val article = get<NewsUI>("article")
                    val isSaved = get<Boolean>("isSaved")
                    if (article != null && isSaved != null) {
                        DetailsScreen(
                            navController = navController,
                            article = article,
                            isSaved = isSaved
                        )
                    }
                }
        }
    }
}