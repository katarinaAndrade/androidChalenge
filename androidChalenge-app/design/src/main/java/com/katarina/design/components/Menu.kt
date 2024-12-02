package com.katarina.design.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.katarina.design.R
import com.katarina.design.theme.AndroidChalengeTheme
import com.katarina.design.theme.Size

@Preview(showBackground = true)
@Composable
fun MenuPreview() {
    AndroidChalengeTheme {
        MenuDropDown(onClick = {
            //todo
        })
    }
}

@Composable
fun MenuDropDown(
    index: Int = 0,
    title: String? = null,
    language: String? = null,
    country: String? = null,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: (Int) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(Size.border, MaterialTheme.colorScheme.primary, RoundedCornerShape(Size.small))
            .padding(
                horizontal = Size.small,
                vertical = Size.tiny
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick(index)
            }
    ) {
        val (titleRef, languageRef, countryRef) = createRefs()

        Text(
            text = title ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(R.string.menu_language, language ?: ""),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(languageRef) {
                    top.linkTo(titleRef.bottom, margin = Size.tiny)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(R.string.menu_country, country ?: ""),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(countryRef) {
                    top.linkTo(languageRef.bottom, margin = Size.tiny)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}