//package com.liebxsantos.pexelswallpapersapp.framework.workmanager
//
//import android.app.WallpaperManager
//import android.content.Context
//import android.graphics.BitmapFactory
//import androidx.hilt.work.HiltWorker
//import androidx.work.*
//import com.liebxsantos.core.domain.model.PhotoDomain
//import com.liebxsantos.pexelswallpapersapp.framework.db.dao.WallpapersDao
//import com.liebxsantos.pexelswallpapersapp.framework.db.entity.toPhotoDomain
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedFactory
//import dagger.assisted.AssistedInject
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import java.net.URL
//import java.util.concurrent.TimeUnit
//import javax.inject.Inject
//import javax.inject.Singleton
//
//private const val WORK_NAME = "WallpaperChanger"
//private const val WORK_INTERVAL_MINUTES = 1L
//
//class WallpaperWorker @AssistedInject constructor(
//    @Assisted val context: Context,
//    @Assisted workerParams: WorkerParameters,
//    private val wallpaperManager: WallpaperManager
//) : CoroutineWorker(context, workerParams) {
//
//    @Inject
//    lateinit var db: WallpapersDao
//
//    override suspend fun doWork(): Result = withContext(Dispatchers.IO){
//
//        try {
//            val photos = mutableListOf<PhotoDomain>()
//            db.getAllPhotos().collect {
//                photos.addAll(it.toPhotoDomain())
//            }
//
//            converterUrlToBitmap(photos.shuffled().first().url)
//            Result.success()
//        }catch (e: Exception){
//            e.printStackTrace()
//            Result.failure()
//        }
//    }
//
//    fun startWorker() {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.UNMETERED)
//            .build()
//
//        val wallpaperChangerRequest =
//            PeriodicWorkRequestBuilder<WallpaperWorker>(WORK_INTERVAL_MINUTES, TimeUnit.MINUTES)
//                .setConstraints(constraints)
//                .build()
//
//        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//            WORK_NAME,
//            ExistingPeriodicWorkPolicy.UPDATE,
//            wallpaperChangerRequest
//        )
//    }
//
//    fun stopWorker() {
//        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
//    }
//
//    private fun converterUrlToBitmap(url: String?) {
//        try {
//            val bitmap = BitmapFactory.decodeStream(URL(url).openStream())
//            wallpaperManager.setBitmap(
//                bitmap,
//                null,
//                true,
//                WallpaperManager.FLAG_SYSTEM)
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//}