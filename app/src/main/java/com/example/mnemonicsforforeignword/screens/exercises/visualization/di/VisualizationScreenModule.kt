package com.example.mnemonicsforforeignword.screens.exercises.visualization.di

import com.example.mnemonicsforforeignword.screens.exercises.visualization.repository.VisualizationRepositoryManager
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class VisualizationScreenModule {
    @Provides
    fun provideRepositoryManager(): VisualizationRepositoryManager =
        VisualizationRepositoryManager()

    @Provides
    fun provideVisualizationViewModelFactory(repositoryManager: VisualizationRepositoryManager): VisualizationViewModelFactory =
        VisualizationViewModelFactory(repositoryManager)
}