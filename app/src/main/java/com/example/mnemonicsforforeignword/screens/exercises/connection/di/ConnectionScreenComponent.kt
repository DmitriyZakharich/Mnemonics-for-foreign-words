package com.example.mnemonicsforforeignword.screens.exercises.connection.di

import com.example.mnemonicsforforeignword.screens.exercises.connection.presentation.view.ConnectionFragment
import dagger.Component

@Component(modules = [ConnectionScreenModule::class])
interface ConnectionScreenComponent {
    fun inject(connectionFragment: ConnectionFragment)
}