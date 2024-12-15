package com.expertcourse.musicplayer

import android.content.Context
import android.media.MediaPlayer

interface MediaPlayerManager {

    fun start()

    fun pause()

    fun next()

    fun previous()

    fun prepare(indexTrack: Int)

    fun release()

    fun isPlay(): Boolean

    class Base(
        private val context: Context
    ) : MediaPlayerManager {

        private var player: MediaPlayer? = null
        private val trackList = listOf(
            R.raw.conversations,
            R.raw.another_love,
            R.raw.its_beautiful_day
        )
        private var currentIndexTrack = 0

        override fun start() {
            player?.start()
        }

        override fun pause() {
            player?.pause()
        }

        override fun next() {
            currentIndexTrack++
            if (currentIndexTrack > LAST_INDEX) currentIndexTrack = START_INDEX
            restart(currentIndexTrack)
        }

        override fun previous() {
            currentIndexTrack--
            if (currentIndexTrack < START_INDEX) currentIndexTrack = LAST_INDEX
            restart(currentIndexTrack)
        }

        override fun prepare(indexTrack: Int) {
            player = MediaPlayer.create(context, trackList[indexTrack])
        }

        override fun release() {
            player?.release()
            player = null
        }

        override fun isPlay(): Boolean = player?.isPlaying == true

        private fun restart(currentIndexTrack: Int) {
            release()
            prepare(currentIndexTrack)
            start()
        }

        companion object {
            private const val START_INDEX = 0
            private const val LAST_INDEX = 2
        }
    }
}