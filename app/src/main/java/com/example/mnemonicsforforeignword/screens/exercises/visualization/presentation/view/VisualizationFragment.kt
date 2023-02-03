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
import androidx.navigation.fragment.NavHostFragment
import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.databinding.FragmentVisualizationBinding
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.DataType
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.intent.WordIntent
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModel
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewmodel.VisualizationViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.viewstate.WordState
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
            savedInstanceState: Bundle?): View? {
        _binding = FragmentVisualizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSpinner()
        binding.nextWordButton.setOnClickListener {
            viewModel.handleIntent(WordIntent.NextWord)
        }

        val navController = NavHostFragment.findNavController(this)
        binding.exitButton.setOnClickListener { navController.navigate(
            com.example.mnemonicsforforeignword.R.id.exercisesMenuFragment) }

        binding.hintButton.setOnClickListener {
            val dialog = VisualizationDialogFragment()
            dialog.show(childFragmentManager, "custom")
        }
    }

    private fun setupViewModel() {
        (requireContext().applicationContext as MyApp).visualizationScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[VisualizationViewModel::class.java]

        viewModel.state.observe(viewLifecycleOwner){
            when (it) {
                is WordState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, "Ошибка. Нет данных", Toast.LENGTH_LONG).show()
                }
                is WordState.Loading -> {
                    binding.wordTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.wordTextView.text = ""
                }

                is WordState.Word -> {
                    binding.progressBar.visibility = View.GONE

                    binding.wordTextView.visibility = View.VISIBLE
                    binding.wordTextView.text = it.nextWord
                }
                is WordState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter(requireActivity(), R.layout.simple_spinner_item, dataType)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)


        binding.dataTypeSpinner.adapter = adapter

        binding.dataTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.handleIntent(WordIntent.LoadingNewWords(DataType.values()[position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                viewModel.handleIntent(WordIntent.LoadingNewWords(DataType.FIGURATIVE_NOUNS))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = VisualizationFragment().apply {}
    }
}