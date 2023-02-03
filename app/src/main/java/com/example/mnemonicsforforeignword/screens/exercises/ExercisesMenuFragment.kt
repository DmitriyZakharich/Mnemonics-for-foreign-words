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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentMenuExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = NavHostFragment.findNavController(this)
        binding.visualizationButton.setOnClickListener { navController.navigate(R.id.visualizationFragment) }
        binding.connectionButton.setOnClickListener { navController.navigate(R.id.connectionFragment) }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExercisesMenuFragment().apply {}
    }
}