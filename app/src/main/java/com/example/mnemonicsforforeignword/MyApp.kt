package com.example.mnemonicsforforeignword

import android.app.Application
import android.content.Context
import com.example.mnemonicsforforeignword.screens.exercises.connection.di.ConnectionScreenComponent
import com.example.mnemonicsforforeignword.screens.exercises.connection.di.DaggerConnectionScreenComponent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.di.DaggerVisualizationScreenComponent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.di.VisualizationScreenComponent

class MyApp: Application() {

    lateinit var visualizationScreenComponent: VisualizationScreenComponent
    lateinit var connectionScreenComponent: ConnectionScreenComponent

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
        connectionScreenComponent = DaggerConnectionScreenComponent.builder().build()
    }

}