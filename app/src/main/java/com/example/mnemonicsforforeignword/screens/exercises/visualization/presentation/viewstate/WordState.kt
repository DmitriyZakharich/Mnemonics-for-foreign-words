package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate

sealed class WordState {
    object Idle : WordState()
    object Loading : WordState()
    data class Word(val nextWord: String) : WordState()
    data class Error(val error: String?) : WordState()
}