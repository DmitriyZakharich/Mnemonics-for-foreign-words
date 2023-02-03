package com.example.mnemonicsforforeignword.screens.exercises.visualization.presentation.view

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mnemonicsforforeignword.R

class VisualizationDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        return builder
            .setTitle("Об упражнении")
            .setMessage("Теория")
            .setPositiveButton("Ок"){ _, _ -> Log.d("TAGqweqweqwe", "PositiveButton: ") }
            .setNegativeButton("Нет"){ _, _ -> Log.d("TAGqweqweqwe", "NegativeButton: ") }
            .setNeutralButton("Подробнее"){ _, _ ->
                val navController = NavHostFragment.findNavController(this)
                navController.navigate( R.id.tutorialMenuFragment) }
            .create()
    }
}