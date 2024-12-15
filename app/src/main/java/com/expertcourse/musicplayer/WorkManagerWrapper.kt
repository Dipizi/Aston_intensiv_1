package com.expertcourse.musicplayer

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

interface WorkManagerWrapper {

    fun start()

    class Base(context: Context) : WorkManagerWrapper {

        private val workManager = WorkManager.getInstance(context)

        override fun start() {
            val workerRequest = OneTimeWorkRequestBuilder<PlayerWorker>().build()
            workManager.enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.KEEP, workerRequest)
        }

        companion object {
            private const val WORK_NAME = "WORK_NAME"
        }
    }
}