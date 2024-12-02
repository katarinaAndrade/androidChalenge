package com.katarina.main.ui.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.katarina.design.components.CardArticles
import com.katarina.design.components.MenuDropDown
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.design.theme.Size
import com.katarina.main.domain.data.model.Article
import com.katarina.main.domain.data.model.SourcesResponse
import com.katarina.main.domain.data.model.TopHeadlinesResponse
import com.katarina.main.ui.R
import com.katarina.main.ui.ui.MainViewModel

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidChalengeTheme {

    }
}

@Composable
fun HomeScreen(
    sources: SourcesResponse?,
    topHeadlines: TopHeadlinesResponse?,
    viewModel: MainViewModel,
    onSelected: (Int) -> Unit
) {
    var showDefaultTitle by remember { mutableStateOf(true) }

    Column {
        sources?.let {
            SourcesDropDown(it) {
                viewModel.getTopHeadlines(it)
                showDefaultTitle = false
            }
        }
        topHeadlines?.let { topHeadlines ->
            topHeadlines.articles?.let {
                if (showDefaultTitle) TitleDefaultTopHeadlines()
                TopHeadlines(it) { index ->
                    onSelected(index)
                }
            }
        }
    }
}

@SuppressWarnings("LongMethod")
@Composable
fun SourcesDropDown(sources: SourcesResponse, onSourceSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val textDefault = stringResource(R.string.dropdown_button)
    var sourceSelected by remember { mutableStateOf(textDefault) }

    val listSize = sources.sources?.size ?: 0

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = Size.standard)
    ) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .height(Size.button)
                .padding(horizontal = Size.small)
        ) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth()
            ) {
                val (textRef, iconRef) = createRefs()

                Text(
                    text = sourceSelected,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(textRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(R.string.dropdown_button_icon),
                    modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(iconRef) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )
            }
        }

        if (expanded) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = Size.small),
                verticalArrangement = Arrangement.spacedBy(Size.tiny)
            ) {
                items(listSize) { index ->
                    MenuDropDown(
                        index = index,
                        title = sources.sources?.get(index)?.name ?: "",
                        language = sources.sources?.get(index)?.language ?: "",
                        country = sources.sources?.get(index)?.country ?: ""
                    ) { id ->
                        expanded = !expanded
                        onSourceSelected(sources.sources?.get(index)?.id ?: "")
                        sourceSelected = sources.sources?.get(index)?.name ?: ""
                    }
                }
            }
        }
    }
}

@Composable
fun TitleDefaultTopHeadlines() {
    Text(
        text = stringResource(R.string.title_default_top_headlines, "US"),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = Size.standard,
                start = Size.small,
                end = Size.small
            )
    )
}

@Composable
fun TopHeadlines(topHeadlines: List<Article>, onArticleSelected: (Int) -> Unit) {
    val listSize = topHeadlines.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Size.standard)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = Size.small),
            verticalArrangement = Arrangement.spacedBy(Size.standard)
        ) {
            items(listSize) { index ->
                CardArticles(
                    index = index,
                    title = topHeadlines[index].title ?: "",
                    description = topHeadlines[index].description ?: "",
                    urlToImage = topHeadlines[index].urlToImage ?: "",
                    publishedAt = topHeadlines[index].formatDate(),
                ) { index ->
                    onArticleSelected(index)
                }
            }
        }
    }
}