package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.CouplesList
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionCoupleIntent
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionDataType
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewstate.ConnectionCoupleState
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository.ConnectionRepositoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConnectionViewModel(private val repositoryManager: ConnectionRepositoryManager) : ViewModel() {

    private val couples = mutableListOf<Pair<String, String>>()
    fun getCouples() : CouplesList = couples

    private var count = 0

    private var _state = MutableLiveData<ConnectionCoupleState>()
    val state: LiveData<ConnectionCoupleState> = _state

    fun handleIntent(intent: ConnectionCoupleIntent) {
        when (intent) {
            is ConnectionCoupleIntent.LoadingNewCouples -> getNewCouples(intent.dataType)
            is ConnectionCoupleIntent.NextCouple -> {
                _state.postValue(
                    if (++count < couples.size) {
                        ConnectionCoupleState.Couples(couples[count]) //result
                    } else {
                        ConnectionCoupleState.Finish
                    }
                )
            }
        }
    }

    private fun getNewCouples(intent: ConnectionDataType){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(ConnectionCoupleState.Loading)

            _state.postValue(try {
                couples.clear()
                couples.addAll ((repositoryManager.getData(intent)))

                if (couples.isNotEmpty()) {
                    count = 0
                    ConnectionCoupleState.Couples(couples.first())
                } else {
                    ConnectionCoupleState.Idle
                }
            } catch (e: Exception) {
                ConnectionCoupleState.Error(e.localizedMessage)
            })
        }
    }
}