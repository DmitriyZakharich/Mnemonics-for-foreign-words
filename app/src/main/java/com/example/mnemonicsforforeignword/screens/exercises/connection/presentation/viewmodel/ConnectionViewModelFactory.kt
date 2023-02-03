package com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.screens.exercises.connection.repository.ConnectionRepositoryManager

class ConnectionViewModelFactory(private val repositoryManager: ConnectionRepositoryManager) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConnectionViewModel(repositoryManager) as T
    }
}