package com.szymon.audiobookplayer.composables

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.szymon.audiobookplayer.ExoPlayerSingleton
import com.szymon.audiobookplayer.audiobooks
import kotlinx.coroutines.CoroutineScope

@Composable
fun AudiobookMainScreen(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val sharedPref = LocalContext.current.getSharedPreferences("last_playback", Context.MODE_PRIVATE)
    val lastPlayedAudio = sharedPref.getInt("last_played_audio", 0)
    val lastPlayedPosition = sharedPref.getLong("last_played_position", 0L)

    var selectedAudiobook by remember { mutableStateOf(audiobooks[lastPlayedAudio]) }
    ExoPlayerSingleton.playAudio(LocalContext.current, selectedAudiobook.audioFileName, audiobooks.indexOf(selectedAudiobook))
    ExoPlayerSingleton.seekTo(LocalContext.current, lastPlayedPosition)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawer(audiobooks, selectedAudiobook, { newAudiobook ->
                selectedAudiobook = newAudiobook
            }, drawerState, scope)
        }
    ) {
        Scaffold(
            topBar = { AudiobookTopBar(drawerState, scope) }
        ) { innerPadding ->
            AudiobookPlayerScreen(selectedAudiobook, Modifier.padding(innerPadding))
        }
    }
}
