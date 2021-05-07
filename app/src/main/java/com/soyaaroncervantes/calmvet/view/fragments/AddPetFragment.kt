package com.soyaaroncervantes.calmvet.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentAddPetBinding
import com.soyaaroncervantes.calmvet.viewmodel.AddPetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class AddPetFragment : Fragment() {
  private lateinit var binding: FragmentAddPetBinding
  private lateinit var addPetViewModel: AddPetViewModel
  private val toolBarViewModel: ToolbarViewModel by activityViewModels()
  private val animals = arrayListOf( "Perro", "Gato", "Cuyo", "Uron", "Conejo", "Pato" )

  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar una mascota")
  }
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAddPetBinding.inflate( inflater, container, false )
    addPetViewModel = ViewModelProvider( this).get( AddPetViewModel::class.java )
    return binding.root
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val animalAutoComplete = binding.animalAutoComplete
    val arrayAdapter = ArrayAdapter( requireContext(), R.layout.list_item, animals )
    animalAutoComplete.setAdapter( arrayAdapter )
    animalAutoComplete.threshold = 1
  }

}