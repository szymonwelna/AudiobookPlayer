package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.Audiobook
import com.szymon.audiobookplayer.ExoPlayerSingleton
import kotlinx.coroutines.delay

@Composable
fun AudiobookPlayerScreen(selectedAudiobook: Audiobook, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentTime by remember { mutableStateOf(0L) }
            var audiobookDuration by remember { mutableStateOf(0L) }

            LaunchedEffect(selectedAudiobook) {
                audiobookDuration = ExoPlayerSingleton.getDuration()
                while (true) {
                    currentTime = ExoPlayerSingleton.getCurrentTime()
                    delay(500)
                }
            }

            AudiobookImage(Modifier, selectedAudiobook.imageRes, selectedAudiobook.title)
            BookTitle(Modifier, selectedAudiobook.title)
            Spacer(modifier = Modifier.height(64.dp))
            AudiobookProgressBar(LocalContext.current)
            NavigationButtons(LocalContext.current)
        }
    }
}