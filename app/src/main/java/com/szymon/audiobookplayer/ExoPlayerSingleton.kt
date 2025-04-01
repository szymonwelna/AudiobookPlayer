package com.szymon.audiobookplayer

import android.content.Context
import android.util.Log
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import java.io.File

object ExoPlayerSingleton {
    private var player: ExoPlayer? = null
    private var currentMediaItem: MediaItem? = null

    private fun getPlayer(context: Context): ExoPlayer {
        if (player == null) {
            player = ExoPlayer.Builder(context).build()
        }
        return player!!
    }

    fun releasePlayer() {
        player?.release()
        player = null
        currentMediaItem = null
    }

    fun playAudio(context: Context, mp3FileName: String, audiobookIndex: Int) {
        try {
            val player = getPlayer(context)
            val mp3File = File(context.filesDir, mp3FileName)
            val mediaItem = MediaItem.fromUri(mp3File.toUri())

            val sharedPref = context.getSharedPreferences("last_playback", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            if (currentMediaItem?.mediaId != mediaItem.mediaId || !player.isPlaying) {
                player.stop()
                player.clearMediaItems()
                player.setMediaItem(mediaItem)
                player.prepare()
                currentMediaItem = mediaItem
                editor.putInt("last_played_audio", audiobookIndex)
                editor.putLong("last_played_position", 0)
                editor.apply()
            }
        } catch (e: Exception) {
            Log.e("ExoPlayerSingleton", "Error playing audio: ${e.message}")
        }
    }

    fun pausePlayer() {
        player?.pause()
    }

    fun resumePlayer() {
        player?.play()
    }

    fun seekTo(context: Context, position: Long) {
        player?.seekTo(position)
        val sharedPref = context.getSharedPreferences("last_playback", Context.MODE_PRIVATE)
        sharedPref.edit().putLong("last_played_position", position).apply()
    }

    fun skipTime(context: Context, millis: Long) {
        val position = (player?.currentPosition ?: 0L) + millis
        player?.seekTo(position)
        val sharedPref = context.getSharedPreferences("last_playback", Context.MODE_PRIVATE)
        sharedPref.edit().putLong("last_played_position", position).apply()
    }

    fun getCurrentTime(): Long {
        return player?.currentPosition ?: 0L
    }

    fun getDuration(): Long {
        return player?.contentDuration ?: 0L
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying ?: false
    }
}