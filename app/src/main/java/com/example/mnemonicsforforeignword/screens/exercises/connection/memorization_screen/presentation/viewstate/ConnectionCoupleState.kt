package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewstate

sealed class ConnectionCoupleState {
    object Idle : ConnectionCoupleState()
    object Loading : ConnectionCoupleState()
    data class Couples(val nextCouple: Pair<String, String>) : ConnectionCoupleState()
    data class Error(val error: String?) : ConnectionCoupleState()
}