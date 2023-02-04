package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionCoupleIntent
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionDataType
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewstate.ConnectionCoupleState
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository.ConnectionRepositoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.StrictMath.random
import kotlin.random.Random

class ConnectionViewModel(private val repositoryManager: ConnectionRepositoryManager) : ViewModel() {

    private val couples = mutableMapOf<String, String>()
    fun getCouples() : Map<String, String> = couples


    private var _state = MutableLiveData<ConnectionCoupleState>()
    val state: LiveData<ConnectionCoupleState> = _state

    fun handleIntent(intent: ConnectionCoupleIntent) {
        when (intent) {
            is ConnectionCoupleIntent.LoadingNewCouples -> getNewCouples(intent.dataType)
            is ConnectionCoupleIntent.NextCouple -> {
                _state.postValue(
                    ConnectionCoupleState.Couples(couples.entries.elementAt(Random.nextInt(couples.size)).toPair())

                    /**Переписать*/
                    //                    if (list.size >= 1 && count < list.size - 1) WordState.Word(list[++count])
                    //                    else WordState.Idle //хрень
                )
            }
        }
    }

    private fun getNewCouples(intent: ConnectionDataType){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(ConnectionCoupleState.Loading)

            _state.postValue(try {
                couples.clear()
                couples.putAll ((repositoryManager.getMapData(intent)))

                if (couples.isNotEmpty()) {
                    ConnectionCoupleState.Couples(couples.entries.elementAt(Random.nextInt(couples.size)).toPair())
                } else {
                    ConnectionCoupleState.Idle
                }
            } catch (e: Exception) {
                ConnectionCoupleState.Error(e.localizedMessage)
            })
        }
    }
}