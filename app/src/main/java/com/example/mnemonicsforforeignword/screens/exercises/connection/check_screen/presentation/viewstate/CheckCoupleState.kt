package com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewstate

sealed class CheckCoupleState {
    data class NewCouple(val nextCouple: Pair<String, String>) : CheckCoupleState()
    object Finish : CheckCoupleState()
    data class Error(val error: String?) : CheckCoupleState()
}