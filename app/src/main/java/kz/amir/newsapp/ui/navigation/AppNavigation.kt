package kz.amir.newsapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
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
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(route = Screen.Home.route) { entry ->
            HomeScreen(
                navController = navController,
                needsToUpdate = entry
                    .savedStateHandle
                    .get<Boolean>("needsToUpdate")
            )
        }
        composable(
            route = Screen.Detail.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(durationMillis = 300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(durationMillis = 300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
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