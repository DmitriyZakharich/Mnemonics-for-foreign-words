package com.example.mnemonicsforforeignword.screens.exercises

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mnemonicsforforeignword.R
import com.example.mnemonicsforforeignword.databinding.FragmentMenuExercisesBinding

class ExercisesMenuFragment : Fragment() {

    private var _binding: FragmentMenuExercisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        _binding = FragmentMenuExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = NavHostFragment.findNavController(this)
        binding.visualizationButton.setOnClickListener { navController.navigate(R.id.visualizationFragment) }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ExercisesMenuFragment().apply {}
    }
}