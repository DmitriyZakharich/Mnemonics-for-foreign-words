package com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.R
import com.example.mnemonicsforforeignword.databinding.FragmentCheckBinding
import com.example.mnemonicsforforeignword.databinding.FragmentConnectionBinding
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewmodel.CheckViewModel
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel.ConnectionViewModel
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel.ConnectionViewModelFactory
import javax.inject.Inject

class CheckFragment : Fragment() {

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!


//    @Inject
//    lateinit var vmFactory: CheckViewModelFactory
    private lateinit var viewModel: CheckViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        val couples = arguments?.getSerializable("couples", HashMap::class.java)
        Toast.makeText(requireContext(), "$couples", Toast.LENGTH_SHORT).show()
    }

    private fun setupViewModel() {
//        (requireContext().applicationContext as MyApp).connectionScreenComponent.inject(this)
        viewModel = ViewModelProvider(this)[CheckViewModel::class.java]
    }


    companion object {
        fun newInstance() = CheckFragment()
    }
}