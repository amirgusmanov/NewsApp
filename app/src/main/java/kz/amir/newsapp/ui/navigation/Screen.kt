package kz.amir.newsapp.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen(route = "home_screen")
    data object Detail : Screen(route = "detail_screen")
}