package com.example.mnemonicsforforeignword.screens.exercises.visualization.di


import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.view.VisualizationFragment
import dagger.Component

@Component(modules = [VisualizationScreenModule::class])
interface VisualizationScreenComponent {
    fun inject(visualizationFragment: VisualizationFragment)
}