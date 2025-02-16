package com.example.timetestapp.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timetestapp.utils.LruCache
import com.example.timetestapp.viewmodels.GenerateViewModel

/**
 *Created by Bharat Lalwani on 16/02/25
 */

@Composable
fun ImageCacheScreen(
    viewModel: GenerateViewModel,
    navController: NavHostController,
    imageCacheUtil: LruCache,
) {

    var refreshData by remember { mutableStateOf(true) }
    val bitmaps = remember { mutableStateListOf<Bitmap>() }
    LaunchedEffect(refreshData) {
        bitmaps.clear()
        bitmaps.addAll(imageCacheUtil.getAllImages())
        refreshData = false
    }

    Column(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center
    ) {
        LazyRow(modifier = Modifier.height(250.dp)) {
            items(bitmaps){
                Image(modifier = Modifier
                    .size(240.dp)
                    .padding(end = 16.dp),
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        Button(
            onClick = {
                imageCacheUtil.clearCache()
                refreshData = true
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Clear Data")
        }
    }

}

