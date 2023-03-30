package com.liebxsantos.pexelswallpapersapp.framework.workmanager

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import com.liebxsantos.pexelswallpapersapp.framework.db.entity.toPhotoDomain
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

//private const val WORK_NAME = "WallpaperChangerTEST"
private const val WORK_INTERVAL_MINUTES = 1L

@HiltWorker
class WorkerTest @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val wallpaperManager: WallpaperManager,
//    private val workManager: WorkManager
): CoroutineWorker(context, workerParams) {

    @Inject
    lateinit var db: WallpapersDao

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val photos = mutableListOf<PhotoDomain>()
            db.getAllPhotos().collect {
                photos.addAll(it.toPhotoDomain())
            }

            converterUrlToBitmap(photos.shuffled().first().url)
            Result.success()
        }catch (e: Exception){
            e.printStackTrace()
            Result.failure()
        }
    }

//    companion object {

//        @SuppressLint("InvalidPeriodicWorkRequestInterval")
//        fun start(workManager: WorkManager) {
//            val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//            val wallpaperWorker = PeriodicWorkRequest.Builder(WorkerTest::class.java, 1, TimeUnit.MINUTES)
//                .setConstraints(constraints)
//                .build()
//
//            workManager.enqueueUniquePeriodicWork(
//                WORK_NAME,
//                ExistingPeriodicWorkPolicy.UPDATE,
//                wallpaperWorker
//            )
//
//            Log.i("WORKTEST", "START")
//        }
//
//        fun cancel(workManager: WorkManager) {
//            workManager.cancelUniqueWork(WORK_NAME)
//            Log.i("WORKTEST", "CANCEL")
//        }
//    }

    private fun converterUrlToBitmap(url: String?) {
        try {
            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
            wallpaperManager.setBitmap(
                bitmap,
                null,
                true,
                WallpaperManager.FLAG_SYSTEM)
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}