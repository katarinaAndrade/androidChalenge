package com.katarina.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.katarina.design.R
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.design.theme.Size

@Preview(showBackground = true)
@Composable
fun NewsPageComponentsPreview() {
    AndroidChalengeTheme {
        ImageComponent("", "")
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageComponent(
    urlToImage: String? = null,
    contentDescription: String? = null
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
        contentDescription = contentDescription,
        error = painterResource(R.drawable.placeholder)
    )
}
