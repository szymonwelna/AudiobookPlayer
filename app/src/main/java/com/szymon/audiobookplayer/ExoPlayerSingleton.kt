package com.szymon.audiobookplayer

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer


object ExoPlayerSingleton {
    private var player: ExoPlayer? = null
    private var currentMediaItem: MediaItem? = null


    fun getPlayer(context: Context): ExoPlayer {
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
        val player = getPlayer(context)

        val assetUri = "asset:///${mp3FileName}"
        val mediaItem = MediaItem.fromUri(assetUri)

        if (currentMediaItem?.mediaId != mediaItem.mediaId) {
            player.stop()
            player.clearMediaItems()
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
            currentMediaItem = mediaItem
        } else {
            player.play()
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
        player?.seekTo(player?.currentPosition!! + millis)
    }

    fun getDuration(): Long {
        return player?.duration ?: 0L
    }

    fun setVolume(volume: Float) {
        player?.volume = volume
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying ?: false
    }
}