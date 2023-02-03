package com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases

import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataType

interface RepositoryManager {
    suspend fun getListData(dataType: DataType): List<String>
}