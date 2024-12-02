package com.katarina.main.ui.ui.newsPage

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.katarina.design.components.ImageComponent
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.design.theme.Size
import com.katarina.main.domain.data.model.Article
import com.katarina.main.ui.R

@Preview(showBackground = true)
@Composable
fun NewsPageScreenPreview() {
    AndroidChalengeTheme {
        NewsPageScreen(
            onBackClick = {
                // Todo
            }
        )
    }
}

@SuppressWarnings("LongMethod")
@Composable
fun NewsPageScreen(
    article: Article? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = Size.standard,
                start = Size.small,
                end = Size.small,
                bottom = Size.standard
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(Size.tiny)
    ) {
        article?.let { article ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                val (backRef, titleRef) = createRefs()

                IconButton(
                    modifier = Modifier.constrainAs(backRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.article_back_button)
                    )
                }

                Text(
                    text = article.source?.name ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(titleRef) {
                            top.linkTo(parent.top)
                            start.linkTo(backRef.end)
                            end.linkTo(parent.end, Size.button)
                            bottom.linkTo(parent.bottom)
                        }
                )
            }

            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.article_author, article.author ?: ""),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = article.formatDate(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth()
            )

            ImageComponent(
                urlToImage = article.urlToImage,
                contentDescription = article.title
            )

            Text(
                text = article.content ?: "",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Size.small)
            )

            article.url?.let { link ->
                val context = LocalContext.current
                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(link)) }

                Text(
                    text = stringResource(R.string.article_link),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            context.startActivity(intent)
                        }
                )
            }
        }
    }
}