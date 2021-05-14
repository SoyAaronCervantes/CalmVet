package com.soyaaroncervantes.calmvet.view.fragments.addPet

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentPetPhotosBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.ToastManager
import com.soyaaroncervantes.calmvet.viewmodel.PetViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PetPhotosFragment : Fragment() {
  private lateinit var binding: FragmentPetPhotosBinding
  private lateinit var animal: Animal
  private val petViewModel: PetViewModel by activityViewModels()
  private val toolBarViewModel: ToolbarViewModel by activityViewModels()

  private var imageCapture: ImageCapture? = null
  private lateinit var outputDirectory: File
  private lateinit var cameraExecutor: ExecutorService

  // Check if we request permission
  private fun hasPermissions() = REQUIRED_PERMISSIONS.all { ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED }

  // Ask for permissions
  private fun requestPermissions() = ActivityCompat.requestPermissions(requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)

  // ask for permissions
  private val requestPermissions = registerForActivityResult(RequestPermission()) {
    if (it) {
      startCamera()
    } else {
      ToastManager.displayToast(requireContext(), "Permisos no aceptados por el usuario")
    }
  }

  override fun onResume() {
    super.onResume()
    toolBarViewModel.setTitle("Agregar Fotos")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentPetPhotosBinding.inflate(inflater, container, false)
    petViewModel.animal.observe(viewLifecycleOwner) { animal = it }
    useCamera()
    return binding.root
  }

  override fun onDestroy() {
    super.onDestroy()
    cameraExecutor.shutdown()
  }

  // Camera functions
  private fun startCamera() {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
    cameraProviderFuture.addListener({
      val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
      val preview = Preview.Builder()
        .build()
        .also { it.setSurfaceProvider(binding.viewFinder.surfaceProvider) }

      imageCapture = ImageCapture.Builder().build()

      val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

      cameraProvider.unbindAll()
      cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)


    }, ContextCompat.getMainExecutor(requireContext()))
  }

  private fun takePhoto() {
    val imageCapture = imageCapture ?: return
    val photoFile = File(
      outputDirectory,
      SimpleDateFormat(FILENAME_FORMAT, Locale.ROOT).format(System.currentTimeMillis()) + ".jpg"
    )
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
    imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
      override fun onError(exc: ImageCaptureException) {
        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
      }

      override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
        val savedURI = Uri.fromFile(photoFile)
        val msg = "Photo capture succeeded: $savedURI"
        animal.headerPhoto = savedURI
        petViewModel.setAnimal( animal );

        ToastManager.displayToast(requireContext(), msg)
        Log.d(TAG, msg)
        Log.d(TAG, animal.toString())
      }
    })
  }

  private fun getOutputDirectory(): File {
    val mediaDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.let {
      File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists()) mediaDir else requireContext().filesDir
  }

  private fun useCamera() {
    if (hasPermissions()) {
      startCamera()
    } else {
      requestPermissions()
    }
    val cameraButton = binding.cameraButton
    cameraButton.setOnClickListener { takePhoto() }
    outputDirectory = getOutputDirectory()
    cameraExecutor = Executors.newSingleThreadExecutor()
    requestPermissions.launch(Manifest.permission.CAMERA)

  }

  companion object {
    private const val TAG = "CameraXBasic"
    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    private const val REQUEST_CODE_PERMISSIONS = 10
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
  }


}