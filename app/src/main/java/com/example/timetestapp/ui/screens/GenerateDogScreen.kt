package com.example.timetestapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timetestapp.R
import com.example.timetestapp.viewmodels.GenerateViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GenerateDogScreen(viewModel: GenerateViewModel, navController: NavHostController) {

    val updatedData = viewModel.dogAPiResponse.collectAsState().value
    var refreshImage by remember { mutableStateOf(false) }

    val buttonBackColor = colorResource(R.color.button_back)
    LaunchedEffect(refreshImage) {
        refreshImage = false
    }

    Column(
        modifier = Modifier.padding(all = 24.dp)
    ) {
        GlideImage(
            imageModel = { updatedData.messageData },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Box(modifier = Modifier.matchParentSize()) {
                    //Empty Icon
                }
            }
        )
        Button(
            onClick = {
                viewModel.fetchRandomDog()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Generate")
        }
    }


}