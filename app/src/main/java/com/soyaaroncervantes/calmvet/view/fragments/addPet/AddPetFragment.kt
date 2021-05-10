package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentAddPetBinding
import com.soyaaroncervantes.calmvet.databinding.ToolbarBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel

class AddPetFragment : Fragment() {
  private lateinit var binding: FragmentAddPetBinding
  private val petViewModel: PetViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentAddPetBinding.inflate( inflater, container, false )
    val view = binding.root
    val toolbar = ToolbarBinding.bind( view )
    toolbar.topAppBar.title = "Agregar Mascotas"
    return view
  }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val animalAutoComplete = binding.animalAutoComplete
    val button = binding.sendToImageFragment

    customizeAutoComplete( animalAutoComplete )
    button.setOnClickListener { validateInputs() }

  }

  private fun customizeAutoComplete( autoCompleteTextView: AutoCompleteTextView ) {

    val arrayAdapter = ArrayAdapter( requireContext(), R.layout.list_item, petViewModel.getAnimalAvailable() )
    autoCompleteTextView.setAdapter( arrayAdapter )
    autoCompleteTextView.threshold = 1
  }

  private fun getGenre( radioGroup: RadioGroup ): CharSequence {
    return when( radioGroup.checkedRadioButtonId ) {
      R.id.radio_button_male -> binding.radioButtonMale.text
      R.id.radio_button_female -> binding.radioButtonFemale.text
      else -> throw Throwable("Error selecting a radio button")
    }
  }

  private fun validateInputs() {
    val inputName = binding.inputPetName.text.toString()
    val inputDescription = binding.inputPetDescription.text.toString()
    val inputAnimal = binding.animalAutoComplete.text.toString()
    val inputAge = binding.inputPetAge.text.toString()
    val radioGroupGenres = binding.radioGroupGenres
    val inputGenre = getGenre( radioGroupGenres ).toString()

    if ( inputName.isBlank() || inputDescription.isBlank() || inputAnimal.isBlank() || inputAge.isBlank() || inputGenre.isBlank() ) {
      val toast = Toast.makeText(requireContext(), "Llena el formulario, para continuar", Toast.LENGTH_SHORT)
      toast.show()
      return
    }

    val animal = Animal( inputName, inputGenre, inputAge, inputAnimal, inputDescription )
    goToTakeAnimalPhotos( animal )

  }

  private fun goToTakeAnimalPhotos(animal: Animal) {
    petViewModel.setAnimal( animal )
    findNavController().navigate(R.id.action_addPetFragment_to_petPhotosFragment)
  }

}