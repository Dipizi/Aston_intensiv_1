package com.expertcourse.musicplayer


class MainViewModel(
    private val playerManager: MediaPlayerManager,
    private val workManager: WorkManagerWrapper
) {

    fun init(): UiState {
        workManager.start()
        return UiState.Play
    }

    fun startTrack(): UiState {
        playerManager.start()
        return UiState.Pause
    }

    fun pauseTrack(): UiState {
        playerManager.pause()
        return UiState.Play
    }

    fun nextTrack() {
        playerManager.next()
    }

    fun previousTrack() {
        playerManager.previous()
    }

    fun isPlay(): Boolean = playerManager.isPlay()
}