package com.soyaaroncervantes.calmvet.view.fragments.splash

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentSplashBinding
import com.soyaaroncervantes.calmvet.services.FirebaseUISignIn
import com.soyaaroncervantes.calmvet.viewmodel.LoginViewModel

class SplashFragment : Fragment() {
  // Binding
  private lateinit var binding: FragmentSplashBinding

  // FirebaseUI
  private lateinit var firebaseUISignIn: FirebaseUISignIn
  private val firebaseUI = AuthUI.getInstance()

  // ViewModels
  private lateinit var loginViewModel: LoginViewModel

  // Content from Login
  private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    validateResult(it)
    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
  }

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    binding = FragmentSplashBinding.inflate( inflater, container, false )

    loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    firebaseUISignIn = FirebaseUISignIn()
    firebaseUISignIn.launchFirebaseUISignIn(getContent)

    return binding.root
  }



  private fun validateResult(result: ActivityResult) {
    val response = IdpResponse.fromResultIntent(result.data)
    val responseIsValidated = validateResponse(response)
    if (!responseIsValidated) { return }

    // If result code is ok, we get currentUser
    if (result.resultCode == Activity.RESULT_OK) {
      loginViewModel.authenticationState
    }

  }
  private fun validateResponse(response: IdpResponse?): Boolean {

    // Check if response isn't null
    if (response === null) {
      return false
    }

    // Check if user has Network Connection
    if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
      Toast.makeText(context, getString(R.string.noNetwork), Toast.LENGTH_LONG).show()
      return false
    }

    // Check if user has Diff Error
    if (response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR) {
      Toast.makeText(context, getString(R.string.unknownError), Toast.LENGTH_LONG).show()
      return false
    }

    return true

  }

  private fun signOutFromFirebase() {
    firebaseUI
      .signOut(requireContext())
      .addOnCompleteListener {
        firebaseUISignIn.launchFirebaseUISignIn(getContent)
      }

  }
 }