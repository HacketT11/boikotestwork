package com.example.boikotest.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.boikotest.R
import com.example.boikotest.domain.models.User
import com.example.boikotest.theme.TestAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun ListScreenComponent(
    onSelectUserAsFavorite: (Int, Boolean) -> Unit,
    onUserClicked: (Int) -> Unit,
    onOnlyFavorite: () -> Unit,
    state: ListScreenState,
) {
    val listState = state.usersPagingData.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { listState.refresh() }, // to use refresh for paging inside of viewmodel(trigger it there), this is the case for ui-based refresh approach, just to save time
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.refresh),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Button(
                onClick = onOnlyFavorite, modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                val favoritesButtonStringRes =
                    if (state.isOnlyFavorites) R.string.full_list else R.string.only_favorites
                Text(
                    text = stringResource(id = favoritesButtonStringRes),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = listState, key = { it.id }) { user ->
                user?.let { ListItemComponent(it, onUserClicked, onSelectUserAsFavorite) }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    TestAppTheme {
        ListScreenComponent(
            onSelectUserAsFavorite = { a, b -> },
            onUserClicked = {},
            onOnlyFavorite = {},
            ListScreenState(
                isOnlyFavorites = false,
                usersPagingData = flowOf()
            )
        )
    }
}