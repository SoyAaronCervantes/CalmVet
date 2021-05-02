package com.soyaaroncervantes.calmvet.view.fragments

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentPetsBinding
import com.soyaaroncervantes.calmvet.services.firebase.FirebaseUISignIn
import com.soyaaroncervantes.calmvet.viewmodel.PetsViewModel
import com.soyaaroncervantes.calmvet.viewmodel.UserProfileViewModel

class PetsFragment : Fragment() {
  // Binding
  private lateinit var binding: FragmentPetsBinding

  // FirebaseUI & Firebase Auth
  private lateinit var firebaseAuth: FirebaseAuth
  private lateinit var firebaseUISignIn: FirebaseUISignIn

  // ViewModel & Adapter
  private val petsViewModel: PetsViewModel by viewModels()
  private val userProfileViewModel: UserProfileViewModel by viewModels(
    factoryProducer = { SavedStateViewModelFactory( Application(), this ) }
  )

  // Content from Login
  private val getContent = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ) { validateResult( it ) }

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    binding = FragmentPetsBinding.inflate( inflater, container, false )
    val view = binding.root

    firebaseUISignIn = FirebaseUISignIn()
    firebaseUISignIn.launchFirebaseUISignIn( getContent )

    return view

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val recyclerView = binding.recyclerViewPets

    recyclerView.apply {
      layoutManager = LinearLayoutManager( view.context )
    }

  }

  private fun validateResult( result: ActivityResult ) {

    val response = IdpResponse.fromResultIntent(result.data)

    val responseIsValidated = validateResponse( response )

    if ( !responseIsValidated ) { return }

    // If result code is ok, we get currentUser
    if (result.resultCode == Activity.RESULT_OK) {

      val user = firebaseAuth.currentUser

      if ( user !== null ) { FirebaseUISignIn.user = firebaseAuth.currentUser!! }

    }

  }

  private fun validateResponse( response: IdpResponse? ): Boolean {

    // Check if response isn't null
    if (response === null) { return false }

    // Check if user has Network Connection
    if ( response.error?.errorCode == ErrorCodes.NO_NETWORK ) {
      Toast.makeText( context, getString(R.string.noNetwork), Toast.LENGTH_LONG).show()
      return false
    }

    // Check if user has Diff Error
    if ( response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR ) {
      Toast.makeText( context, getString(R.string.unknownError), Toast.LENGTH_LONG).show()
      return false
    }

    return true

  }

}