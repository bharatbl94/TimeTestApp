package com.example.timetestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object Utils {
    fun hasInternet(context: Context): Boolean {
        var result: Boolean
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                connectivityManager.activeNetwork ?: return false
            } else {
                val netInfo = connectivityManager.activeNetworkInfo
                return netInfo != null && netInfo.isConnected
            }
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }
}
object ScreenViewNav {
    var MainScreenView = "MainScreenView"
    var RandomGenerateScreenView = "RandomGenerateScreenView"
    var ListScreenView = "ListScreenView"
}

