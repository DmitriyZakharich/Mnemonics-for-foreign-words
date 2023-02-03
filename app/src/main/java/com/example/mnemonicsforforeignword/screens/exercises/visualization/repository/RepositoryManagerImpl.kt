package com.example.mnemonicsforforeignword.screens.exercises.visualization.repository

import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases.RepositoryManager
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataType
import java.lang.Integer.min

class RepositoryManagerImpl : RepositoryManager {

    private enum class Genders {
        MALE, NEUTER, FEMALE, PLURAL
    }

    override suspend fun getListData(dataType: DataType): List<String> {
        return when (dataType) {
            DataType.FIGURATIVE_NOUNS -> getFigurativeNouns()
            DataType.ABSTRACT_NOUNS -> getData("abstract_nouns.txt")
            DataType.ADJECTIVE -> getAdjectives()
            DataType.VERB -> getData("verbs.txt")
            DataType.ALL_TYPES -> getData("figurative_nouns.txt", "abstract_nouns.txt", "adjectives.txt", "verbs.txt")
            DataType.ADJECTIVE_AND_NOUN -> getDataFromPair("adjectives.txt", "figurative_nouns.txt")
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
    private fun getFigurativeNouns(): List<String> {
        return loadListFromFile("figurative_nouns.txt")
            .map { it.substringBefore("|") }
    }

    private fun getAdjectives (): List<String>
        = getAdjectivesRandomGender( loadListFromFile("adjectives.txt") )

    private fun getAdjectivesRandomGender(adjectives: List<String>): List<String>
        = adjectives.map {
            val index = ((0..3).random())
            it.split('|') [index]
        }

    private fun getAdjectiveGender(gender: Genders, adjective: String): String =
        adjective.split('|')[gender.ordinal]

    private suspend fun getData(vararg files: String): List<String> {
        val resultList = mutableListOf<String>()
        files.forEach {
            resultList.addAll(
                if (it == "adjectives.txt")
                    getAdjectives()
                else
                    loadListFromFile(it))
        }
        return resultList.shuffled()
    }

    //Словосочение прилагательное + существительное
    private fun getDataFromPair(vararg files: String): List<String> {
        val adjectives = mutableListOf<String>()
        val figurativeNouns = mutableListOf<String>()
        val resultList = mutableListOf<String>()

        files.forEachIndexed {index, fileName  ->
            val list = loadListFromFile(fileName)
            when (index) {
                0 -> adjectives.addAll(list)
                1 -> figurativeNouns.addAll(list)
            }
        }

        adjectives.shuffle()
        figurativeNouns.shuffle()
        val minSize = min(adjectives.size, figurativeNouns.size)

        for (i in 0 until minSize) {
            val resultAdjective = when (figurativeNouns[i].substringAfter('|')) {
                "м" -> getAdjectiveGender(Genders.MALE, adjectives[i] )
                "с" -> getAdjectiveGender(Genders.NEUTER, adjectives[i] )
                "ж" -> getAdjectiveGender(Genders.FEMALE, adjectives[i] )
                "н" -> getAdjectiveGender(Genders.PLURAL, adjectives[i] )    //множественное число (ножницы)
                else -> getAdjectiveGender(Genders.MALE, adjectives[i] )
            }
            resultList.add(resultAdjective + " " + figurativeNouns[i].substringBefore("|"))
        }
        return resultList
    }
}