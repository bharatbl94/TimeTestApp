package com.example.timetestapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.timetestapp.utils.LruCache
import com.example.timetestapp.utils.ScreenViewNav
import com.example.timetestapp.viewmodels.GenerateViewModel


@Composable
fun ScreenNavigation(
    navController: NavHostController,
    generateViewModel: GenerateViewModel = viewModel(),
    imageCacheUtil: LruCache
) {
    NavHost(navController = navController, startDestination = ScreenViewNav.MainScreenView) {
        composable(ScreenViewNav.MainScreenView) {
            MainScreenView(navController)
        }
        composable(
            route = ScreenViewNav.RandomGenerateScreenView,
        ) { _ ->
            GenerateDogScreen(
                viewModel = generateViewModel,
                navController,
                imageCacheUtil
            )
        }
        composable(
            route = ScreenViewNav.ListScreenView,
        ) { _ ->

            ImageCacheScreen(
                viewModel = generateViewModel,
                navController = navController,
                imageCacheUtil = imageCacheUtil,
            )
        }
    }
}