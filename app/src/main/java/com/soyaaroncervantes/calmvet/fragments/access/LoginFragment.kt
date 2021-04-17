package com.soyaaroncervantes.calmvet.fragments.access

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
  private lateinit var binding: FragmentLoginBinding
  private lateinit var googleSignInClient: GoogleSignInClient
  private lateinit var auth: FirebaseAuth
  private val getContent = registerForActivityResult( ActivityResultContracts.StartActivityForResult() ) { result: ActivityResult ->
    if ( result.resultCode == Activity.RESULT_OK ) {
      val task = GoogleSignIn.getSignedInAccountFromIntent( result.data )
      val account = task.getResult(ApiException::class.java)
       firebaseAuthWithGoogle(account?.idToken ?: "")
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
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
      auth.signInWithEmailAndPassword(email, password)
    }

    binding.googleButton.setOnClickListener { signIn() }
  }

  private fun signIn() {
    val signInIntent = googleSignInClient.signInIntent
    getContent.launch( signInIntent )
  }

  private fun firebaseAuthWithGoogle(IDToken: String) {
    val googleCredential = GoogleAuthProvider.getCredential(IDToken, null)
    val user = auth.signInWithCredential(googleCredential).result?.user
    if (user != null) {
      Log.d("User", "${user.uid}: $user")
    } else {
      Log.e("Error User", "Something were wrong")
    }
  }

  private fun getGoogleSignInOptions(): GoogleSignInOptions {
    return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestIdToken( getString(R.string.default_web_client_id) )
      .requestEmail()
      .build()
  }

}