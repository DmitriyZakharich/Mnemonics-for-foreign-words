package com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases

import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataIntent

interface RepositoryManager {
    suspend fun getListData(dataIntent: DataIntent): List<String>
}