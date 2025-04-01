package com.szymon.audiobookplayer.composables

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.C
import androidx.media3.common.Player
import com.szymon.audiobookplayer.ExoPlayerSingleton
import com.szymon.audiobookplayer.formatTime
import kotlinx.coroutines.delay

@Composable
fun AudiobookProgressBar(context: Context) {
    var currentTime by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (ExoPlayerSingleton.getDuration() == C.TIME_UNSET) {
            delay(50)
        }

        while (true) {
            if (!isDragging) {
                currentTime = ExoPlayerSingleton.getCurrentTime()
                duration = ExoPlayerSingleton.getDuration()
            }
            delay(100)
        }
    }

    if (!isDragging) {
        currentTime = ExoPlayerSingleton.getCurrentTime()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Slider(
            value = currentTime.toFloat(),
            onValueChange = {
                currentTime = it.toLong()
                isDragging = true
            },
            onValueChangeFinished = {
                ExoPlayerSingleton.seekTo(context, currentTime)
                isDragging = false
            },
            valueRange = if (duration > 0) 0f..duration.toFloat() else 0f..1f,
            modifier = Modifier.fillMaxWidth(),
            enabled = duration > 0
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentTime),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = formatTime(duration),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}