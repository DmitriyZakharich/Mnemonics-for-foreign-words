package com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.intent.ConnectionCoupleIntent
import com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.intent.ConnectionDataType
import com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.viewstate.ConnectionCoupleState
import com.example.mnemonicsforforeignword.screens.exercises.connection.repository.ConnectionRepositoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionViewModel(private val repositoryManager: ConnectionRepositoryManager) : ViewModel() {

    private val couples = mutableMapOf<String, String>()

    private var _state = MutableLiveData<ConnectionCoupleState>()
    val state: LiveData<ConnectionCoupleState> = _state

    fun handleIntent(intent: ConnectionCoupleIntent) {
        when (intent) {
            is ConnectionCoupleIntent.LoadingNewCouples -> getNewCouples(intent.dataType)
            is ConnectionCoupleIntent.NextCouple -> {
                val randomKey = couples.keys.random()
                val value = couples[randomKey]
                _state.postValue(
                    ConnectionCoupleState.Couples(Pair(randomKey, value!!))

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
                    val randomKey = couples.keys.random()
                    val value = couples[randomKey]
                    ConnectionCoupleState.Couples(Pair(randomKey, value!!))
                } else {
                    ConnectionCoupleState.Idle
                }
            } catch (e: Exception) {
                ConnectionCoupleState.Error(e.localizedMessage)
            })
        }
    }
}