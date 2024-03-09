package com.example.movieapp.Profile.logic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel: ViewModel() {
    private val _clickedMethod = MutableStateFlow("PayPal")
    val clickedDay = _clickedMethod.asStateFlow()

    fun whichMethodIsClicked(name: String) {
        _clickedMethod.value = name
    }

}