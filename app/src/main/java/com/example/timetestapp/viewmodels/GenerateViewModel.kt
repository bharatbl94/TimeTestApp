package com.example.timetestapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timetestapp.GenerateDogRepository
import com.example.timetestapp.model.BaseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenerateViewModel : ViewModel() {

    private val repository = GenerateDogRepository()
    private val _dogAPiResponse = MutableStateFlow(BaseModel(""))
    val dogAPiResponse: StateFlow<BaseModel> get() = _dogAPiResponse

    fun fetchRandomDog() {
        viewModelScope.launch {
            repository.getRandomDog().collect { apiResponse ->
                _dogAPiResponse.emit(apiResponse)
            }
        }
    }



}