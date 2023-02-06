package com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mnemonicsforforeignword.CouplesList
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewstate.CheckCoupleState
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewstate.ConnectionCoupleState
import java.util.*

class CheckViewModel(couples: CouplesList?) : ViewModel() {

    private val couplesQueue: Queue<Pair<String, String>> = LinkedList()

    private var _state = MutableLiveData<CheckCoupleState>()
    val state: LiveData<CheckCoupleState> = _state

    init {
        if (!couples.isNullOrEmpty())
            couplesQueue.addAll(couples)
        else
            _state.value = CheckCoupleState.Error("Ошибка. Нет данных.")
    }

    fun getNextCouple() {
        val couple = couplesQueue.poll()
        if (couple != null)
            _state.value = CheckCoupleState.NewCouple(couple)
        else
            _state.value = CheckCoupleState.Finish
        //TODO("Сделать диалог окно с переходом на экран запоминания")
    }
}