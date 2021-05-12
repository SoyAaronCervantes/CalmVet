package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.Manifest
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.soyaaroncervantes.calmvet.databinding.FragmentPetPhotosBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel
import java.io.File
import java.util.*

class PetPhotosFragment : Fragment() {
  private lateinit var binding: FragmentPetPhotosBinding
  private lateinit var animal: Animal
  private val petViewModel: PetViewModel by activityViewModels()
  private val toolBarViewModel: ToolbarViewModel by activityViewModels()

  private fun hasCameraPermissions() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
  private fun hasGalleryPermissions() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
  private fun hasWritePermissions() = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

  private val requestTakePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {

  }

  private val requestPickPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { photo ->
    Glide
      .with(this)
      .load(photo)
      .fitCenter()
      .into(binding.imageViewProfile)
  }

  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar Fotos");
  }

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
      hasCameraPermissions()
      hasWritePermissions()
      launchCamera()
    }

    galleryButton.setOnClickListener {
      hasGalleryPermissions()
      launchGallery()
    }
  }


  private fun selectPhoto() {
    showToast("Pick a photo here")
  }

  // Launch Camera
  private fun launchCamera() {
    val currentPhotoPath = createImageFile()
    val photoUri = currentPhotoPath.absoluteFile.toUri()

    requestTakePictureLauncher.launch(photoUri)

  }

  private fun launchGallery() {
    requestPickPictureLauncher.launch("image/*")
  }


  private fun createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy-MM:dd-HH:mm:ss", Locale.ROOT).format(Date())
    val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    return File.createTempFile(
      "JPEG_${timeStamp}_",
      "jpg",
      storageDir
    );
  }

  // #Toast
  private fun showToast(message: String) {
    val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    toast.show()
  }
}