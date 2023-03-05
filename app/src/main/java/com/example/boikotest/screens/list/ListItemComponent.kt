package com.example.boikotest.screens.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.boikotest.R
import com.example.boikotest.domain.models.User
import com.example.boikotest.theme.Favorite
import com.example.boikotest.theme.TestAppTheme

@Composable
fun ListItemComponent(
    user: User,
    onItemClick: (Int) -> Unit,
    onFavoriteClick: (Int, Boolean) -> Unit
) {

    val starColor =
        animateColorAsState(targetValue = if (user.isFavorite) Favorite else MaterialTheme.colors.onBackground)

    Row(
        modifier = Modifier
            .clickable { onItemClick(user.id) }
            .fillMaxWidth()
            .padding(vertical = 8.dp) // just for pagination

    ) {
        Text(
            text = user.email,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colors.onBackground
        )

        Image(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = null,
            colorFilter = ColorFilter.tint(starColor.value),
            modifier = Modifier
                .clickable { onFavoriteClick(user.id, user.isFavorite) }
                .padding(8.dp)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    TestAppTheme {
        ListItemComponent(
            user = User(1, "vladyslavboiko98@gmail.com", "", "", "", true),
            {},
            { a, v -> }
        )
    }
}