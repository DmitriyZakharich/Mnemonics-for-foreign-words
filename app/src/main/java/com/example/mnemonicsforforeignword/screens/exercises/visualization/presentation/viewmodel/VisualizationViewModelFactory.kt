package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.screens.exercises.visualization.repository.VisualizationRepositoryManager

class VisualizationViewModelFactory (private val repositoryManager: VisualizationRepositoryManager):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VisualizationViewModel(repositoryManager) as T
    }
}