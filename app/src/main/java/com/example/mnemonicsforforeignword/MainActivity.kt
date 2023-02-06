package com.example.mnemonicsforforeignword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.view.CheckFragment
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.view.ConnectionFragment

class MainActivity : AppCompatActivity(), ConnectionFragment.OnFragmentSendDataListener,
    CheckFragment.OnFragmentGetDataListener
{

    private var data: CouplesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSendData(data: CouplesList?) {
        this.data = data
    }

    override fun onGetData(): CouplesList? = data
}