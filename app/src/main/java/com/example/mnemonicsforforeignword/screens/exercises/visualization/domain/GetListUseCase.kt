package com.example.mnemonicsforforeignword.screens.exercises.visualization.domain

import com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases.RepositoryManager
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataIntent

/**
 * Берет List
 *  - преобразует прилагательные в прил с разными родами
 *  - преоразует окончания в паре прил+сущ
 */
class GetListUseCase(private val repository: RepositoryManager) {

    suspend fun getListData(dataIntent: DataIntent): List<String> {
        return repository.getListData(dataIntent)
    }

    private fun test() : Any {



        val m = mapOf<String, String>()
        val l = listOf<String>()
        return m
    }


}