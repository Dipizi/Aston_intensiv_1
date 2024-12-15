package com.expertcourse.musicplayer

import android.app.Application

class MusicApp : Application() {

    lateinit var player: MediaPlayerManager
    lateinit var viewModel: MainViewModel
    private lateinit var workManager: WorkManagerWrapper

    override fun onCreate() {
        super.onCreate()
        workManager = WorkManagerWrapper.Base(this)
        player = MediaPlayerManager.Base(this)
        viewModel = MainViewModel(player, workManager)
    }
}