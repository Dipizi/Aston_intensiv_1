package com.expertcourse.musicplayer

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class PlayerWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        (applicationContext as MusicApp).player.prepare(0)
        return Result.success()
    }
}