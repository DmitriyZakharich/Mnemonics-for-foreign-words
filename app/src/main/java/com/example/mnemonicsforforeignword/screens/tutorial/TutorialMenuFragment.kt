package com.example.mnemonicsforforeignword.screens.tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mnemonicsforforeignword.R

class TutorialMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tutorial_menu, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = TutorialMenuFragment().apply {}
    }
}