package com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mnemonicsforforeignword.CouplesList
import com.example.mnemonicsforforeignword.databinding.FragmentCheckBinding
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewmodel.CheckViewModel
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewmodel.CheckViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.connection.check_screen.presentation.viewstate.CheckCoupleState


class CheckFragment : Fragment() {

    internal interface OnFragmentGetDataListener {
        fun onGetData(): CouplesList?
    }

    private lateinit var viewModel: CheckViewModel

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        subscribeToViewModel()
        setupButtons()
    }

    private fun setupViewModel() {
        val activity = requireActivity() as OnFragmentGetDataListener
        val couples = activity.onGetData()

        val vmFactory = CheckViewModelFactory(couples)
        viewModel = ViewModelProvider(this, vmFactory)[CheckViewModel::class.java]
        viewModel.getNextCouple()
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CheckCoupleState.NewCouple -> {
                    binding.secondWordTextview.visibility = View.INVISIBLE
                    binding.firstWordTextview.text = it.nextCouple.first
                    binding.secondWordTextview.text = it.nextCouple.second
                }
                is CheckCoupleState.Finish -> Toast.makeText(requireContext(), "Финиш",
                    Toast.LENGTH_SHORT).show()
                is CheckCoupleState.Error -> Toast.makeText(requireContext(), "Ошибка",
                    Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setupButtons() {
        binding.secondWordTextview.setOnClickListener { binding.secondWordTextview.visibility = View.VISIBLE }
        binding.checkYourselfButton.setOnClickListener { binding.secondWordTextview.visibility = View.VISIBLE }
        binding.nextCoupleButton.setOnClickListener {viewModel.getNextCouple()}
    }

    companion object {
        fun newInstance() = CheckFragment()
    }
}