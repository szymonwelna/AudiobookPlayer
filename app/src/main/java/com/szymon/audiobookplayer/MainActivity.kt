package com.szymon.audiobookplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.szymon.audiobookplayer.ui.theme.AudiobookTheme
import com.szymon.audiobookplayer.composables.AudiobookMainScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudiobookTheme {
                AudiobookMainScreen()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        ExoPlayerSingleton.releasePlayer()
    }
}