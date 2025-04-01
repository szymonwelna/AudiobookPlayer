package com.szymon.audiobookplayer

import android.content.Context
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
        val sharedPref = getSharedPreferences("last_played_audio", Context.MODE_PRIVATE)
        sharedPref.edit().putLong("last_played_position", ExoPlayerSingleton.getCurrentTime()).apply()
        ExoPlayerSingleton.releasePlayer()
    }
}