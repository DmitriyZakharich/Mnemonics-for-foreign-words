package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository.ConnectionRepositoryManager

class ConnectionViewModelFactory(private val repositoryManager: ConnectionRepositoryManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConnectionViewModel(repositoryManager) as T
    }
}