package com.katarina.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.katarina.design.R
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.design.theme.Size

@Preview(showBackground = true)
@Composable
fun CardArticlesPreview() {
    AndroidChalengeTheme {
        CardArticles(
            onClick = {
                //todo
            })
    }
}

@Composable
fun CardArticles(
    index: Int = 0,
    title: String? = null,
    description: String? = null,
    urlToImage: String? = null,
    publishedAt: String? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick(index)
            }
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(Size.imageHeight)
                .clip(
                    RoundedCornerShape(
                        topEnd = Size.tiny,
                        topStart = Size.tiny,
                        bottomEnd = Size.tiny,
                        bottomStart = Size.tiny
                    )
                ),
            model = urlToImage,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.card_image),
            error = painterResource(R.drawable.placeholder)
        )

        Text(
            text = title ?: "",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Size.tiny)
        )

        Text(
            text = description ?: "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = publishedAt ?: "",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Size.tiny)
        )
    }
}