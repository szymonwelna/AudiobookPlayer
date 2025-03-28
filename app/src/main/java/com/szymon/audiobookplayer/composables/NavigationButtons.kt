package com.szymon.audiobookplayer.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.szymon.audiobookplayer.ExoPlayerSingleton

@Composable
fun NavigationButtons() {
    var isPlaying by remember { mutableStateOf(ExoPlayerSingleton.isPlaying()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { ExoPlayerSingleton.skipTime(-10000) },
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Replay10,
                contentDescription = "Przewiń wstecz 10s",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = {
                if (ExoPlayerSingleton.isPlaying()) {
                    ExoPlayerSingleton.pausePlayer()
                    isPlaying = false
                } else {
                    ExoPlayerSingleton.resumePlayer()
                    isPlaying = true
                }
                      },
            modifier = Modifier.size(80.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause
                else Icons.Filled.PlayArrow,
                contentDescription = if (isPlaying) "Pauza"
                else "Odtwórz",
                modifier = Modifier.size(64.dp)
            )
        }
        IconButton(
            onClick = { ExoPlayerSingleton.skipTime(10000) },
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Forward10,
                contentDescription = "Przewiń do przodu 10s",
                modifier = Modifier.size(48.dp)
            )
        }
    }
}