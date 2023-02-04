package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent

sealed class ConnectionCoupleIntent{
    data class LoadingNewCouples(val dataType: ConnectionDataType) : ConnectionCoupleIntent()
    object NextCouple : ConnectionCoupleIntent()
}

enum class ConnectionDataType {
    FIGURATIVE_NOUNS,
    ABSTRACT_NOUNS,
    ALL_NOUNS
}