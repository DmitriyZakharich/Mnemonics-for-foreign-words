package com.example.mnemonicsforforeignword

import android.app.Application
import android.content.Context
import com.example.mnemonicsforforeignword.screens.exercises.visualization.di.DaggerVisualizationScreenComponent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.di.VisualizationScreenComponent

class MyApp: Application() {

    lateinit var visualizationScreenComponent: VisualizationScreenComponent

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        visualizationScreenComponent = DaggerVisualizationScreenComponent.builder().build()
    }

}