package com.example.timetestapp.utils

/**
 *Created by Bharat Lalwani on 17/02/25
 */

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Environment.isExternalStorageRemovable
import com.example.timetestapp.utils.Utils.decodeFromBase64
import com.example.timetestapp.utils.Utils.encodeToBase64

import kotlinx.coroutines.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


data class CacheImage(var isDiskCache:Boolean = false,var file:File,var time:Long, var bitmap: Bitmap? = null)

class LruCache(private val context: Context) :
    LinkedHashMap<String, CacheImage>(4, 0.75f, true) {

    fun putImage(key: String, value: Bitmap) {
        CoroutineScope(Dispatchers.IO).launch {
            val file = File(getCacheDirectory(), formatKey(key))
            put(key, CacheImage(isDiskCache = false,file = file, bitmap = value, time = 0L))
            putBitmapIntoCache(file, value)
        }
    }

    fun clearCache(){
        clear()
    }

    suspend fun getAllImages(): List<Bitmap> {
        if (size < 1)
            return emptyList()
        return withContext(Dispatchers.Main) {
            async { getAllBitmap() }.await()
        }
    }

    private suspend fun getAllBitmap(): List<Bitmap> = withContext(Dispatchers.IO) {
        val allCacheImages = mutableListOf<Bitmap>()
        entries.reversed().forEach {
            if (it.value.file.exists()){
                allCacheImages.add(BitmapFactory.decodeFile(it.value.file.path))
            }
        }
        allCacheImages
    }

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, CacheImage>?): Boolean {
        return if (size > 4) {
            //Remove from
            eldest?.value?.file?.let {
                removeFromDisk(it)
            }
            true
        } else {
            false
        }
    }

    private fun removeFromDisk(file: File){
        deleteDirectory(file)
    }

    private suspend fun putBitmapIntoCache(file: File, bitmap: Bitmap) {
        withContext(Dispatchers.IO) {
            val outputStream = BufferedOutputStream(FileOutputStream(file))
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()
        }
    }

    private fun formatKey(str: String?): String {
        val formatted = "${System.currentTimeMillis()}_${str}"
        return formatted.encodeToBase64()
    }


    private fun deleteDirectory(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children = dir.list()
            if (children != null) {
                for (i in children.indices) {
                    val success = deleteDirectory(File(dir, children[i]))
                    if (!success) {
                        return false
                    }
                }
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }

    private fun getCacheDirectory(): File {
        val cachePath =
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
                || !isExternalStorageRemovable()
            ) {
                context.externalCacheDir?.path
            } else {
                context.cacheDir.path
            }
        val file = File(cachePath + File.separator + "DogImageCache")
        if (!file.exists())
            file.mkdirs()
        return file
    }
}