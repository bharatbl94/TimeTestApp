package com.example.timetestapp.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("message") var messageData: String = "",
    @SerializedName("status") var statusResponse: String = ""
)