package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.di

import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel.ConnectionViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.repository.ConnectionRepositoryManager
import dagger.Module
import dagger.Provides

@Module
class ConnectionScreenModule {
    @Provides
    fun provideRepositoryManager(): ConnectionRepositoryManager =
        ConnectionRepositoryManager()

    @Provides
    fun provideConnectionViewModelFactory(repositoryManager: ConnectionRepositoryManager): ConnectionViewModelFactory =
        ConnectionViewModelFactory(repositoryManager)
}