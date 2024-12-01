package com.katarina.main.ui.ui.newsPage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.katarina.design.theme.AndroidChalengeTheme

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

@Composable
fun NewsPageScreen(onBackClick: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (back) = createRefs()

        onBackClick()


    }
}
