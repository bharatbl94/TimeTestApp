package com.example.timetestapp.ui.screens

import android.graphics.Bitmap
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.timetestapp.utils.LruCache
import com.skydoves.landscapist.glide.GlideImage

/**
 *Created by Bharat Lalwani on 16/02/25
 */

@Composable
fun CachedImageGlide(url: String, modifier: Modifier = Modifier, imageCache: LruCache) {
    val context = LocalContext.current
//    val imageCache = remember { ImageCacheUtil() }

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(url) {
        val cachedBitmap = imageCache[url]?.bitmap
        if (cachedBitmap != null) {
            bitmap = cachedBitmap
        } else {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                    ) {
                        imageCache.putImage(url, resource)
                        bitmap = resource
                    }

                    override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {}
                })
        }
    }

    bitmap?.let {
        Image(bitmap = it.asImageBitmap(),
            contentDescription = "Image",
            modifier = modifier,
            contentScale = ContentScale.FillBounds
            )
    } ?: GlideImage(
        imageModel = { url },
        modifier = modifier
    )
}