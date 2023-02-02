package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.databinding.FragmentVisualizationBinding
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataIntent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModel
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModelFactory
import javax.inject.Inject

class VisualizationFragment : Fragment() {

    private var _binding: FragmentVisualizationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: VisualizationViewModelFactory
    private lateinit var viewModel: VisualizationViewModel

    private val dataType =
        arrayOf("Образные существительные", "Абстрактные существительные", "Прилагательные",
            "Глаголы", "Прилагательные + существительные", "Все типы")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        _binding = FragmentVisualizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSpinner()
    }

    private fun setupViewModel() {
        (requireContext().applicationContext as MyApp).visualizationScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[VisualizationViewModel::class.java]

        viewModel.words.observe(viewLifecycleOwner) {
            Toast.makeText(MyApp.applicationContext(), it.joinToString(), Toast.LENGTH_SHORT).show()
        }

//        viewModel.getWords(DataIntent.FIGURATIVE_NOUNS)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(requireActivity(), R.layout.simple_spinner_item, dataType)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

//        Toast.makeText(requireActivity(), "setupSpinner", Toast.LENGTH_SHORT).show()

        binding.dataTypeSpinner.adapter = adapter

        binding.dataTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.getWords(DataIntent.values()[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                viewModel.getWords(DataIntent.FIGURATIVE_NOUNS)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = VisualizationFragment().apply {}
    }
}