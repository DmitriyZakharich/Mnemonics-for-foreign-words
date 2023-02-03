package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate

sealed class VisualizationWordState {
    object Idle : VisualizationWordState()
    object Loading : VisualizationWordState()
    data class Couples(val nextWord: String) : VisualizationWordState()
    data class Error(val error: String?) : VisualizationWordState()
}