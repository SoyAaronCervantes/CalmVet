package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentPetPhotosBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel
import java.io.File
import java.util.concurrent.ExecutorService

class PetPhotosFragment : Fragment() {
  private lateinit var binding: FragmentPetPhotosBinding
  private lateinit var animal: Animal
  private val petViewModel: PetViewModel by activityViewModels()
  private val toolBarViewModel: ToolbarViewModel by activityViewModels()
  private var buttonSelected: Int? = null

  private val preview: Preview? = null
  private val imageCapture: ImageCapture? = null
  private val imageAnalyzer: ImageAnalysis? = null
  private val camera: Camera? = null

  private lateinit var outputDirectory: File;
  private lateinit var cameraExecutor: ExecutorService

  private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
    if (isGranted) usesPhotoFunction() else showToast("Permisos rechazados")
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
      buttonSelected = it.id
      val cameraPermission = hasCameraPermissions()
      if (cameraPermission) usesPhotoFunction() else launchCamera()

    }
    galleryButton.setOnClickListener {
      buttonSelected = it.id
      val galleryPermission = hasGalleryPermissions()
      if (galleryPermission) usesPhotoFunction() else launchGallery()

    }
    outputDirectory = getOutputDirectory()
  }


  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar Fotos");
  }

  private fun usesPhotoFunction() {
    when (buttonSelected) {
      R.id.cameraButton -> takePhoto()
      R.id.galleryButton -> selectPhoto()
      else -> throw Throwable("Error on functionality")
    }
  }

  private fun getOutputDirectory(): File {
    val mediaDirectory = requireContext().getExternalFilesDir(null)?.absoluteFile.let {
      File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    return if (mediaDirectory != null && mediaDirectory.exists()) mediaDirectory else requireContext().filesDir
  }

  private fun takePhoto() {}
  private fun selectPhoto() {}

  // #Launchers
  private fun launchCamera() {
    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
  }

  private fun launchGallery() {
    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
  }

  // #Toast
  private fun showToast(message: String) {
    val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
    toast.show()
  }
}