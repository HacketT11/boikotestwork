package com.example.boikotest.screens.user

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.boikotest.R
import kotlinx.coroutines.flow.Flow

@Composable
fun UserScreenEventProcessor(events: Flow<UserScreenEvent>) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        events.collect {
            when (it) {
                CallError -> {
                    Toast.makeText(context, R.string.something_wrong, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}