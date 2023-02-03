package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.VisualizationDataType
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.VisualizationWordIntent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate.VisualizationWordState
import com.example.mnemonicsforforeignword.screens.exercises.visualization.repository.VisualizationRepositoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisualizationViewModel(private val repositoryManager: VisualizationRepositoryManager) : ViewModel() {

    private val list = mutableListOf<String>()

    private var _state = MutableLiveData<VisualizationWordState>()
    val state: LiveData<VisualizationWordState> = _state

    fun handleIntent(intent: VisualizationWordIntent) {
        when (intent) {
            is VisualizationWordIntent.LoadingNewWords -> getNewWords(intent.dataType)
            is VisualizationWordIntent.NextWord -> {
                _state.postValue(
                    VisualizationWordState.Couples(list.random())

                    /**Переписать*/
//                    if (list.size >= 1 && count < list.size - 1) WordState.Word(list[++count])
//                    else WordState.Idle //хрень
                )
            }
        }
    }



    private fun getNewWords(intent: VisualizationDataType){
        viewModelScope.launch(Dispatchers.IO){
            _state.postValue(VisualizationWordState.Loading)

            _state.postValue(try {
                list.clear()
                list.addAll ((repositoryManager.getListData(intent)))

                if (list.isNotEmpty()) {
                    VisualizationWordState.Couples( list.random() )
                } else {
                    VisualizationWordState.Idle
                }
            } catch (e: Exception) {
                VisualizationWordState.Error(e.localizedMessage)
            })
        }
    }
}