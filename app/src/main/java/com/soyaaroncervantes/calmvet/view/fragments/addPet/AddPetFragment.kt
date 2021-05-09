package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
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
    val inputGenre = getGenre( radioGroupGenres ).toString()
    val button = binding.sendToImageFragment

    val animal = Animal( inputName, inputGenre, inputAge, inputAnimal, inputDescription )

    val arrayAdapter = ArrayAdapter( requireContext(), R.layout.list_item, animals )
    animalAutoComplete.setAdapter( arrayAdapter )
    animalAutoComplete.threshold = 1

    button.setOnClickListener {
      if ( !validateInputs() ) {
        val toast = Toast.makeText(requireContext(), "Llena el formulario, para continuar", Toast.LENGTH_SHORT)
        toast.show()
      } else {
        goToTakeAnimalPhotos( animal )
      }
    }

  }

  private fun getGenre( radioGroup: RadioGroup ): CharSequence {
    return when( radioGroup.checkedRadioButtonId ) {
      R.id.radio_button_male -> binding.radioButtonMale.text
      R.id.radio_button_female -> binding.radioButtonFemale.text
      else -> ""
    }
  }

  private fun goToTakeAnimalPhotos(animal: Animal) {
    petViewModel.setAnimal( animal )
  }

  private fun validateInputs(): Boolean {
    val inputName = binding.inputPetName.text.isNullOrEmpty()
    val inputDescription = binding.inputPetDescription.text.isNullOrEmpty()
    val inputAnimal = binding.animalAutoComplete.text.isNullOrEmpty()
    val inputAge = binding.inputPetAge.text.isNullOrEmpty()
    val radioGroupGenres = binding.radioGroupGenres
    val inputGenre = getGenre( radioGroupGenres ).isEmpty()

    if ( !inputName || !inputDescription || !inputAnimal || !inputAge || !inputGenre ) {
      return false
    }

    return true

  }

}