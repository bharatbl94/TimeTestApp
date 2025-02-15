package com.example.timetestapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.timetestapp.utils.ScreenViewNav
import com.example.timetestapp.viewmodels.GenerateViewModel


@Composable
fun ScreenNavigation(
    navController: NavHostController,
    generateViewModel: GenerateViewModel = viewModel()
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
                navController
            )
        }
        composable(
            route = ScreenViewNav.ListScreenView,
        ) { _ ->
            GenerateDogScreen(
                viewModel = generateViewModel,
                navController = navController
            )
        }
    }
}