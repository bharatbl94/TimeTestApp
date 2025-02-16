package com.example.timetestapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.timetestapp.ui.screens.ScreenNavigation
import com.example.timetestapp.ui.theme.TimeTestAppTheme
import com.example.timetestapp.utils.LruCache
import com.example.timetestapp.viewmodels.GenerateViewModel

class MainActivity : ComponentActivity() {
    private val generateViewModel: GenerateViewModel by viewModels()
    lateinit var navController : NavHostController
    val imageCacheUtil = LruCache(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TimeTestAppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                navController = rememberNavController()
                    ScreenNavigation(
                        navController = navController,
                        generateViewModel,
                        imageCacheUtil

//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
                    )
                }
//            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimeTestAppTheme {
//        MainScreenView()
    }
}