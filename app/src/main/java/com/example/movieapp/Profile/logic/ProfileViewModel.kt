package com.example.movieapp.Profile.logic

import android.util.Log
import androidx.compose.ui.util.fastJoinToString
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel: ViewModel() {
    private val _clickedMethod = MutableStateFlow("PayPal")
    val clickedDay = _clickedMethod.asStateFlow()

    fun whichMethodIsClicked(name: String) {
        _clickedMethod.value = name
    }

    private val _cardValue = MutableStateFlow("")
    val cardValue = _cardValue.asStateFlow()


    fun collectValue(text: String) {
        if (text.length == 16) {
            val maskedString = "**** **** **** " + text.takeLast(4)
            _cardValue.value = maskedString
        }
    }
}