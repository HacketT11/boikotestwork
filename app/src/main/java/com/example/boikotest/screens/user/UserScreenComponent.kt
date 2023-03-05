package com.example.boikotest.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.boikotest.R
import com.example.boikotest.domain.models.User
import com.example.boikotest.theme.Favorite
import com.example.boikotest.theme.TestAppTheme

@Composable
fun UserScreenComponent(state: UserScreenState, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .clickable { onBack() }
                .padding(8.dp)
        )

        if (state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        state.user?.let { user ->
            Row(modifier = Modifier.padding(start = 8.dp, top = 40.dp)) {
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.id, user.id),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.email, user.email),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.first_name, user.firstName),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.last_name, user.lastName),
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            model = user.avatarUrl,
                            contentDescription = null,
                            modifier = Modifier.size(56.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(if (user.isFavorite) Favorite else MaterialTheme.colors.onBackground)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    TestAppTheme {
        UserScreenComponent(
            UserScreenState(
                user = User(
                    1,
                    "vladyslavboiko98@gmail.com",
                    "Vladyslav",
                    "Boiko",
                    "",
                    true
                ),
                false
            )
        ) {}
    }
}