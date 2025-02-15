package com.example.timetestapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.timetestapp.utils.ScreenViewNav


@Composable
fun MainScreenView(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.padding(24.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navController.navigate(ScreenViewNav.RandomGenerateScreenView)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Generate Dogs Screen")
        }
        Button(
            onClick = {

            },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        ) {
            Text(text = "My Recently Generated Dogs Screen")
        }
    }
}