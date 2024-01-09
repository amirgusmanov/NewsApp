package kz.amir.newsapp.ui.navigation

import android.util.Log
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
            //todo: fix crash when pop back stack on details screen
            val article = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<NewsUI>("article")
            Log.d("article", "AppNavigation: $article")
            DetailsScreen(navController = navController, article = article!!)
        }
    }
}