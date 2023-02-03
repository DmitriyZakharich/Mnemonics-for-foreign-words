package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases.RepositoryManager
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataType
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.WordIntent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate.WordState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisualizationViewModel(private val repositoryManager: RepositoryManager) : ViewModel() {

    private var count = -1
    private val list = mutableListOf<String>()

    private var _state = MutableLiveData<WordState>()
    val state: LiveData<WordState> = _state

    fun handleIntent(intent: WordIntent) {
        when (intent) {
            is WordIntent.LoadingNewWords -> getNewWords(intent.dataType)
            is WordIntent.NextWord -> {
                _state.postValue(
                    WordState.Word(list.random())

                    /**Переписать*/
//                    if (list.size >= 1 && count < list.size - 1) WordState.Word(list[++count])
//                    else WordState.Idle //хрень
                )
            }
        }
    }

    private fun getNewWords(intent: DataType){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(WordState.Loading)

            _state.postValue(try {
                list.clear()
                list.addAll ((repositoryManager.getListData(intent)))

                if (list.isNotEmpty()) {
                    WordState.Word( list.random() )
                } else {
                    WordState.Idle
                }
            } catch (e: Exception) {
                WordState.Error(e.localizedMessage)
            })
        }
    }
}