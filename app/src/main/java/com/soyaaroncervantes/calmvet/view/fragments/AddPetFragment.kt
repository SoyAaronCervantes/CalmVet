package com.soyaaroncervantes.calmvet.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentAddPetBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.viewmodel.AddPetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class AddPetFragment : Fragment() {
  private lateinit var binding: FragmentAddPetBinding
  private lateinit var addPetViewModel: AddPetViewModel
  private val petViewModel: PetViewModel by activityViewModels()
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
    val inputName = binding.inputPetName.text.toString()
    val inputDescription = binding.inputPetDescription.text.toString()
    val inputAnimal = binding.animalAutoComplete.text.toString()
    val inputAge = binding.inputPetAge.text.toString()
    val radioGroupGenres = binding.radioGroupGenres
    val inputGenre = getGenre( radioGroupGenres )
    val button = binding.sendToImageFragment

    val animal = Animal( inputName, inputGenre, inputAge, inputAnimal, inputDescription )

    val arrayAdapter = ArrayAdapter( requireContext(), R.layout.list_item, animals )
    animalAutoComplete.setAdapter( arrayAdapter )
    animalAutoComplete.threshold = 1

    button.setOnClickListener {
      goToTakeAnimalPhotos( animal )
    }

  }

  private fun getGenre( radioGroup: RadioGroup ): String {
    return when( radioGroup.checkedRadioButtonId ) {
      R.id.radio_button_male -> binding.radioButtonMale.text
      else -> binding.radioButtonFemale.text
    }.toString()
  }

  private fun goToTakeAnimalPhotos(animal: Animal) {
    petViewModel.setAnimal( animal )
    findNavController().navigate(R.id.action_petsFragment_to_addPetFragment)
  }

}