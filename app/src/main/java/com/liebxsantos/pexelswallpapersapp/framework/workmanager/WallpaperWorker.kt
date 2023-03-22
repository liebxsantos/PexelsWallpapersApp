package com.liebxsantos.pexelswallpapersapp.framework.workmanager

import android.content.Context
import androidx.work.*

import java.util.concurrent.TimeUnit

class WallpaperWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    override fun doWork(): Result {




        return Result.success()
    }

    companion object {
        private const val WORK_NAME = "WallpaperChanger"
        private const val WORK_INTERVAL_MINUTES = 15L

        fun start(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

            val wallpaperChangerRequest = PeriodicWorkRequestBuilder<WallpaperWorker>(WORK_INTERVAL_MINUTES, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context). enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                wallpaperChangerRequest
            )
        }

        fun stop(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }

}