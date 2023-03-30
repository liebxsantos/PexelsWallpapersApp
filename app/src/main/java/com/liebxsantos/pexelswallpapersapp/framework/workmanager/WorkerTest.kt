package com.liebxsantos.pexelswallpapersapp.framework.workmanager

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@HiltWorker
class WorkerTest @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val wallpaperManager: WallpaperManager,
): CoroutineWorker(context, workerParams) {

    @Inject
    lateinit var db: WallpapersDao

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val photo = db.getRandomDownloadedWallpaper()
            converterUrlToBitmap(photo.urlPhoto)

            Result.success()
        }catch (e: Exception){
            e.printStackTrace()
            Result.failure()
        }
    }

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