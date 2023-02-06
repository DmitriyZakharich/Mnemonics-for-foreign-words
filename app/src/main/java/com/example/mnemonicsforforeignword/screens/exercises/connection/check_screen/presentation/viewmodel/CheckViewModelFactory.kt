package com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.CouplesList

class CheckViewModelFactory(private val couples: CouplesList?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CheckViewModel(couples) as T
    }
}