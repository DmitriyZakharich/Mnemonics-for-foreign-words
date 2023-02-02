package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases.RepositoryManager
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VisualizationViewModel(private val repositoryManager: RepositoryManager) : ViewModel() {

    private var _words = MutableLiveData<List<String>>()
    val words: LiveData<List<String>> = _words

    fun getWords(intent: DataIntent){
        viewModelScope.launch(Dispatchers.IO){
            _words.postValue(repositoryManager.getListData(intent))
        }
    }
}