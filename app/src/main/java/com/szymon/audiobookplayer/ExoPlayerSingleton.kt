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

    fun playAudio(context: Context, mp3FileName: String) {
        try {
            val player = getPlayer(context)
            val mp3File = File(context.filesDir, mp3FileName)
            val mediaItem = MediaItem.fromUri(mp3File.toUri())

            if (currentMediaItem?.mediaId != mediaItem.mediaId || !player.isPlaying) {
                player.stop()
                player.clearMediaItems()
                player.setMediaItem(mediaItem)
                player.prepare()
                currentMediaItem = mediaItem
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

    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    fun skipTime(millis: Long) {
        player?.seekTo((player?.currentPosition ?: 0L) + millis)
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