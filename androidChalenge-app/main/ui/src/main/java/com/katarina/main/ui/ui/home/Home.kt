package com.katarina.main.ui.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.main.domain.data.model.Article
import com.katarina.main.domain.data.model.TopHeadlines

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidChalengeTheme {
        HomeScreen(null)
    }
}

@Composable
fun HomeScreen(listArticles: TopHeadlines?) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (topHeadlines) = createRefs()
        listArticles?.let { list ->
            list.articles?.let { articles ->
                val sources = articles.map { it.source?.name ?: "" }.distinct()
                LazyColumn(
                    modifier = Modifier.constrainAs(topHeadlines) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    item {}
                }
            }
        }
    }
}

@Composable
fun TopHeadlines(article: Article) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        val (image, title, description) = createRefs()
    }
}
