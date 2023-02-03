package com.example.mnemonicsforforeignword.screens.exercises.connection.di

import com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.viewmodel.ConnectionViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.connection.repository.ConnectionRepositoryManager
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