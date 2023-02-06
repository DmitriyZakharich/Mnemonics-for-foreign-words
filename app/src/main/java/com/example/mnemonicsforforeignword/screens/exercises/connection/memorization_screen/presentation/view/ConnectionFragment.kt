package com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.view

import android.content.Context
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
import com.example.mnemonicsforforeignword.CouplesList
import com.example.mnemonicsforforeignword.MyApp
import com.example.mnemonicsforforeignword.R
import com.example.mnemonicsforforeignword.databinding.FragmentConnectionBinding
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionCoupleIntent
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.intent.ConnectionDataType
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel.ConnectionViewModel
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewmodel.ConnectionViewModelFactory
import com.example.mnemonicsforforeignword.screens.exercises.connection.memorization_screen.presentation.viewstate.ConnectionCoupleState
import javax.inject.Inject


class ConnectionFragment : Fragment() {

    internal interface OnFragmentSendDataListener {
        fun onSendData(data: CouplesList?)
    }

    private lateinit var fragmentSendDataListener: OnFragmentSendDataListener

    private var _binding: FragmentConnectionBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var vmFactory: ConnectionViewModelFactory
    private lateinit var viewModel: ConnectionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentSendDataListener = context as OnFragmentSendDataListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context должен реализовывать интерфейс OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        _binding = FragmentConnectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupSpinner()
        setupButtons()
    }

    private fun setupViewModel() {
        (requireContext().applicationContext as MyApp).connectionScreenComponent.inject(this)
        viewModel = ViewModelProvider(this, vmFactory)[ConnectionViewModel::class.java]

        viewModel.state.observe(viewLifecycleOwner){
            when (it) {
                is ConnectionCoupleState.Idle -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(this.activity, "Ошибка. Нет данных", Toast.LENGTH_LONG).show()
                }
                is ConnectionCoupleState.Loading -> {
                    binding.coupleTextView.visibility = View.GONE
                    binding.progressBar2.visibility = View.VISIBLE
                    binding.coupleTextView.text = ""
                }

                is ConnectionCoupleState.Couples -> {
                    binding.progressBar2.visibility = View.GONE

                    binding.coupleTextView.visibility = View.VISIBLE
                    binding.coupleTextView.text = it.nextCouple.first + " - " + it.nextCouple.second
                }
                is ConnectionCoupleState.Finish -> {
                    Toast.makeText(this.activity, "Финиш", Toast.LENGTH_LONG).show()
                }
                is ConnectionCoupleState.Error -> {
                    binding.progressBar2.visibility = View.GONE
                    Toast.makeText(this.activity, it.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupSpinner() {
        val dataType = arrayOf("Образные существительные", "Абстрактные существительные", "Все существительные")

        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, dataType)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.dataTypeSpinner.adapter = adapter

        binding.dataTypeSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.handleIntent(
                    ConnectionCoupleIntent.LoadingNewCouples(ConnectionDataType.values()[position]))
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                viewModel.handleIntent(
                    ConnectionCoupleIntent.LoadingNewCouples(ConnectionDataType.FIGURATIVE_NOUNS))
            }
        }
    }

    private fun setupButtons() {
        binding.nextCoupleButton.setOnClickListener {
            viewModel.handleIntent(ConnectionCoupleIntent.NextCouple)
        }

        binding.toCheckButton.setOnClickListener {

            fragmentSendDataListener.onSendData(viewModel.getCouples())
            val navController = NavHostFragment.findNavController(this)
            navController.navigate(R.id.checkFragment)

//            val navController = NavHostFragment.findNavController(this)
//            navController.navigate(R.id.checkFragment, bundle)
        }

//        val navController = NavHostFragment.findNavController(this)
//        binding.exitButton.setOnClickListener { navController.navigate(
//            com.example.mnemonicsforforeignword.R.id.exercisesMenuFragment) }
//
//        binding.hintButton.setOnClickListener {
//            val dialog = VisualizationDialogFragment()
//            dialog.show(childFragmentManager, "custom")
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConnectionFragment().apply {}
    }
}