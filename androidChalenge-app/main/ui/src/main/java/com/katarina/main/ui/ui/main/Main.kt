package com.katarina.main.ui.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
    val sources = viewModel.sourceResponse
    val topHeadlines = viewModel.topHeadlinesResponse

    val selectedArticle = remember { mutableIntStateOf(0) }

    NavHost(navController, startDestination = Screens.HOME, modifier) {
        composable(Screens.HOME) {
            HomeScreen(sources, topHeadlines, viewModel) { index ->
                selectedArticle.value = index
                navController.navigate(Screens.NEWS_PAGE)
            }
        }
        composable(Screens.NEWS_PAGE) {
            NewsPageScreen(
                article = topHeadlines?.articles?.get(selectedArticle.value),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
