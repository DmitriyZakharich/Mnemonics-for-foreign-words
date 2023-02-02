package com.example.mnemonicsforforeignword.screens.exercises.visualization.di

import com.example.mnemonicsforforeignword.screens.exercises.visualization.repository.RepositoryManagerImpl
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.visualization.interfases.RepositoryManager
import dagger.Module
import dagger.Provides

@Module
class VisualizationScreenModule {
    @Provides
    fun provideRepositoryManager(): RepositoryManager =
        RepositoryManagerImpl()

    @Provides
    fun provideListViewModelFactory(repositoryManager: RepositoryManager): VisualizationViewModelFactory =
        VisualizationViewModelFactory(repositoryManager)
}