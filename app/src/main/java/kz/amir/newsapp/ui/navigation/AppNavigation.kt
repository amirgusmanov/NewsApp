package kz.amir.newsapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, tween(300)
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(300)
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right, tween(300)
            )
        }
    ) {
        composable(route = Screen.Home.route) { entry ->
            HomeScreen(
                navController = navController,
                needsToUpdate = entry
                    .savedStateHandle
                    .get<Boolean>("needsToUpdate")
            )
        }
        composable(route = Screen.Detail.route) {
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