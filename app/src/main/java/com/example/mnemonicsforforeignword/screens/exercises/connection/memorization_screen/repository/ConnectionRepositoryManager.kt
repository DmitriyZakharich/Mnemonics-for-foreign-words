package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository

import com.example.mnemonicsforforeignword.CouplesList
import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionDataType

class ConnectionRepositoryManager {

    fun getData(dataType: ConnectionDataType): CouplesList {
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
    private fun getFigurativeNouns(): CouplesList {
        return loadListFromFile("figurative_nouns.txt")
                .shuffled()
                .map { it.substringBefore("|") }
                .toMutableList()
                .listToPairsList()
    }

    private fun getAbstractNouns(): CouplesList {
        return loadListFromFile("abstract_nouns.txt")
                .shuffled()
                .toMutableList()
                .listToPairsList()
    }

    private fun getAllNouns(): CouplesList {
        val listFigurative = loadListFromFile("figurative_nouns.txt")
            .map { it.substringBefore("|") }
        val listAbstract = loadListFromFile("abstract_nouns.txt")
        val newList = listFigurative + listAbstract
        return newList
             .shuffled()
            .toMutableList()
            .listToPairsList()
    }

    private fun MutableList<String>.listToPairsList(): CouplesList {
        val result = mutableListOf<Pair<String, String>>()
        if (this.size % 2 != 0) this.removeLast()
        for (index in this.indices step 2) {
            result.add(Pair(this[index], this[index + 1]))
        }
        return result
    }
}

