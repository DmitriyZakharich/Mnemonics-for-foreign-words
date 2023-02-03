package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent

sealed class WordIntent{
    data class LoadingNewWords(val dataType: DataType) : WordIntent()
    object NextWord : WordIntent()
}

enum class DataType {
    FIGURATIVE_NOUNS,
    ABSTRACT_NOUNS,
    ADJECTIVE,
    VERB,
    ADJECTIVE_AND_NOUN,
    ALL_TYPES
}