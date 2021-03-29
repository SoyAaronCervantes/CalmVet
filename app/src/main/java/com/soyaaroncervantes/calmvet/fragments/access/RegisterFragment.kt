package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.factory.AuthFactory

class RegisterFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_register, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


    val loginButton: MaterialButton = view.findViewById(R.id.loginButton)

    /** Submit Button */
    val submitButton: MaterialButton = view.findViewById(R.id.submitButton)

    /** Inputs */
    val emailInput: TextInputEditText = view.findViewById(R.id.emailInputEdit)
    val passwordInput: TextInputEditText = view.findViewById(R.id.passwordInputEdit)


    loginButton.setOnClickListener {
      it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    submitButton.setOnClickListener { viewElement ->

      val email = emailInput.text.toString()
      val password = passwordInput.text.toString()

      val user = AuthFactory.FactoryParams.User(email, password);


      AuthFactory.register(user)
        .addOnSuccessListener {
          viewElement.findNavController().navigate(R.id.action_registerFragment_to_personalDataFragment)
        }
        .addOnFailureListener {

          val text = "Invalid email or Password"
          val duration = Toast.LENGTH_LONG
          val toast = Toast.makeText(activity, text, duration)
          toast.show()

        }

    }

    /** toDo Implement Factory Pattern for Login & Auth */

  }
}