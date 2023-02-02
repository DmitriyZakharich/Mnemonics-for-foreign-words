package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate

sealed class ListState {
    object Idle : ListState()
    object Loading : ListState()
    data class Words(val list: List<String>) : ListState()
    data class Error(val error: String?) : ListState()
}