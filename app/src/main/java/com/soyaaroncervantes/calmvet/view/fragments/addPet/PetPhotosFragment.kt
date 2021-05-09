package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.soyaaroncervantes.calmvet.databinding.FragmentPetPhotosBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class PetPhotosFragment : Fragment() {
  private lateinit var binding: FragmentPetPhotosBinding
  private lateinit var animal: Animal
  private val petViewModel: PetViewModel by activityViewModels()
  private val toolBarViewModel: ToolbarViewModel by activityViewModels()

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    binding = FragmentPetPhotosBinding.inflate( inflater, container, false )
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    petViewModel.animal.observe( viewLifecycleOwner ) { animal = it }
  }

  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar Fotos");
  }
}