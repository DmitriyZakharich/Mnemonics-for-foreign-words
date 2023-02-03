package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent

sealed class VisualizationWordIntent{
    data class LoadingNewWords(val dataType: VisualizationDataType) : VisualizationWordIntent()
    object NextWord : VisualizationWordIntent()
}

enum class VisualizationDataType {
    FIGURATIVE_NOUNS,
    ABSTRACT_NOUNS,
    ADJECTIVE,
    VERB,
    ADJECTIVE_AND_NOUN,
    ALL_TYPES
}