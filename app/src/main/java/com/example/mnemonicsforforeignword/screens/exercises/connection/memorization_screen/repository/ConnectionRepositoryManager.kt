package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository

import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionDataType

class ConnectionRepositoryManager {

    fun getMapData(dataType: ConnectionDataType): Map<String, String> {
        return when (dataType) {
            ConnectionDataType.FIGURATIVE_NOUNS -> getFigurativeNouns()
            ConnectionDataType.ABSTRACT_NOUNS -> getAbstractNouns()
            ConnectionDataType.ALL_NOUNS -> getAllNouns()
        }
    }

    private fun loadListFromFile(file: String): List<String> {
        val text: String = MyApp.applicationContext().assets
            .open(file)
            .bufferedReader().use {
                it.readText()
            }
        return text.lines()
    }

    //Отдельно, потому что нужна отчистка от указания рода
    private fun getFigurativeNouns(): Map<String, String> {
        return loadListFromFile("figurative_nouns.txt")
                .shuffled()
                .map { it.substringBefore("|") }
                .zipWithNext()
                .toMap()
    }

    private fun getAbstractNouns(): Map<String, String> {
        return loadListFromFile("abstract_nouns.txt")
                .shuffled()
                .zipWithNext()
                .toMap()
    }

    private fun getAllNouns(): Map<String, String> {
        val listFigurative = loadListFromFile("figurative_nouns.txt")
            .map { it.substringBefore("|") }
        val listAbstract = loadListFromFile("abstract_nouns.txt")
        val newList = listFigurative + listAbstract
        return newList
             .shuffled()
             .zipWithNext()
             .toMap()
    }
}