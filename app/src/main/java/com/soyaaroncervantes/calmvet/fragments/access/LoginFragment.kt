package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentLoginBinding
import com.soyaaroncervantes.calmvet.factory.AuthFactory

class LoginFragment : Fragment() {
  private lateinit var firebaseAuth: FirebaseAuth
  private lateinit var binding: FragmentLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    firebaseAuth = Firebase.auth
  }

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {

    val binding: FragmentLoginBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
    this.binding = binding
    return binding.root

  }

  override fun onStart() {
    super.onStart()
    val currentUser = firebaseAuth.currentUser
    if (currentUser != null) {
      Log.d("[DEBUG FIREBASE]", "Current User: ${currentUser.uid}")
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    /** Register Button */
    val registerButton: MaterialButton = binding.registerButton

    /** Submit Button */
    val submitButton: MaterialButton = view.findViewById( R.id.submitButton )

    /** Inputs */
    val emailInput: TextInputEditText = view.findViewById( R.id.emailInputEdit )
    val passwordInput: TextInputEditText = view.findViewById( R.id.passwordInputEdit )


    registerButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    submitButton.setOnClickListener { viewElement ->

      val email = emailInput.text.toString()
      val password = passwordInput.text.toString()

      val user = AuthFactory.FactoryParams.User( email, password );


      AuthFactory.login(user)
        .addOnSuccessListener {
          viewElement.findNavController().navigate(R.id.action_loginFragment_to_petFragment)
        }
        .addOnFailureListener {

          val text = "Invalid email or Password"
          val duration = Toast.LENGTH_LONG
          val toast = Toast.makeText( activity, text, duration )
          toast.show()

        }

    }

  }

}