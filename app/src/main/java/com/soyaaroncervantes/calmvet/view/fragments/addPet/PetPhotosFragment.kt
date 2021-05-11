package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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

  private val requestTakePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview() ) { bitmap ->
    val previewView = binding.previewViewCamera
    
  }

  private val requestPickPictureLauncher = registerForActivityResult( ActivityResultContracts.GetContent() ) {

  }

  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar Fotos");
  }

  private fun hasCameraPermissions() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
  private fun hasGalleryPermissions() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentPetPhotosBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    petViewModel.animal.observe(viewLifecycleOwner) { animal = it }
    val cameraButton = binding.cameraButton
    val galleryButton = binding.galleryButton

    cameraButton.setOnClickListener {
      val cameraPermission = hasCameraPermissions()
      launchCamera()
    }

    galleryButton.setOnClickListener {
      val galleryPermission = hasGalleryPermissions()
      launchGallery()
    }
  }

  private fun takePhoto() {
    showToast("Take photo here")
  }
  private fun selectPhoto() {
    showToast("Pick a photo here")
  }

  private fun launchCamera() {  } // <- Launch Camera
  private fun launchGallery() {
    requestPickPictureLauncher.launch("image/*")
  } // <- Launch Gallery

  // #Toast
  private fun showToast(message: String) {
    val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    toast.show()
  }
}