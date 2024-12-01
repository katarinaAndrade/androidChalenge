package com.katarina.main.ui.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.katarina.main.domain.util.Screens
import com.katarina.main.ui.ui.MainViewModel
import com.katarina.main.ui.ui.home.HomeScreen
import com.katarina.main.ui.ui.newsPage.NewsPageScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainAppNav(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<MainViewModel>()
    val navController = rememberNavController()

    viewModel.getTopHeadlines()
    NavHost(navController, startDestination = Screens.HOME, modifier) {
        composable(Screens.HOME) {
            HomeScreen(null)
        }
        composable(Screens.NEWS_PAGE) {
            NewsPageScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
