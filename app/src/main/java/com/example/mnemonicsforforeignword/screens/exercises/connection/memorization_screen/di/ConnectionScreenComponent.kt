package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.di

import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.view.ConnectionFragment
import dagger.Component

@Component(modules = [ConnectionScreenModule::class])
interface ConnectionScreenComponent {
    fun inject(connectionFragment: ConnectionFragment)
}