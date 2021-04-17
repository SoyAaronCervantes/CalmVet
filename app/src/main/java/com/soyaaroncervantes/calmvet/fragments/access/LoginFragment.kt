package com.soyaaroncervantes.calmvet.fragments.access

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentLoginBinding
import com.soyaaroncervantes.calmvet.databinding.SigninSocialMediaBinding

class LoginFragment : Fragment() {
  private lateinit var binding: FragmentLoginBinding
  private lateinit var socialMediaBinding: SigninSocialMediaBinding
  private lateinit var googleSignInClient: GoogleSignInClient
  private lateinit var auth: FirebaseAuth
  private val getContent = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ) { result: ActivityResult ->
    if ( result.resultCode == Activity.RESULT_OK ) {
      val task = GoogleSignIn.getSignedInAccountFromIntent( result.data )
      task.addOnCompleteListener {
        val account = it.getResult(ApiException::class.java)
        firebaseAuthWithGoogle(account?.idToken ?: "")
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    binding = FragmentLoginBinding.inflate( inflater, container, false )
    socialMediaBinding = SigninSocialMediaBinding.bind( binding.root )
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    auth = Firebase.auth
    googleSignInClient = GoogleSignIn.getClient( requireContext() , getGoogleSignInOptions())
    auth.signOut()

    /** Register Button */
    val registerButton: MaterialButton = binding.registerButton

    /** Submit Button */
    val submitButton: MaterialButton = view.findViewById(R.id.submitButton)

    /** Inputs */
    val emailInput: TextInputEditText = view.findViewById(R.id.emailInputEdit)
    val passwordInput: TextInputEditText = view.findViewById(R.id.passwordInputEdit)


    registerButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    submitButton.setOnClickListener {
      val email = emailInput.text.toString()
      val password = passwordInput.text.toString()
      val signInMethod = auth.signInWithEmailAndPassword(email, password)
      taskForSignIn( signInMethod )
    }

    socialMediaBinding.googleButton.setOnClickListener { signIn() }
  }

  private fun signIn() {
    val signInIntent = googleSignInClient.signInIntent
    getContent.launch( signInIntent )
  }

  private fun firebaseAuthWithGoogle(IDToken: String) {
    val credential = GoogleAuthProvider.getCredential(IDToken, null)
    signInWithCredential( credential )
  }

  private fun getGoogleSignInOptions(): GoogleSignInOptions {
    return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken( getString(R.string.default_web_client_id) )
      .requestEmail()
      .requestProfile()
      .requestId()
      .build()
  }

  private fun signInWithCredential( credential: AuthCredential ) {
    val signInMethod = auth.signInWithCredential( credential )
    taskForSignIn( signInMethod )
  }

  private fun taskForSignIn( task: Task<AuthResult> ) {
    task.addOnSuccessListener {
      if ( it.user != null ) {
        val user = auth.currentUser
        Log.d("User", "${user?.uid}: $user")
      }
    }
  }

}