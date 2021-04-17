package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.auth.Authentication
import com.soyaaroncervantes.calmvet.databinding.FragmentRegisterBinding
import com.soyaaroncervantes.calmvet.interfaces.FirebaseAuthMethods
import com.soyaaroncervantes.calmvet.models.user.UserAuth

class RegisterFragment : Fragment(), FirebaseAuthMethods {
  private lateinit var binding: FragmentRegisterBinding

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {

    val binding: FragmentRegisterBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
    this.binding = binding
    return binding.root

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

      register( email, password ).addOnSuccessListener {
        val action = R.id.action_registerFragment_to_personalDataFragment
        viewElement.findNavController().navigate( action )
      }
    }

  }

  private fun register( email: String, password: String ): Task<AuthResult> {

    val firebaseAuth = Firebase.auth
    val authentication = Authentication( firebaseAuth )

    val auth = authByEmailAndPassword( email, password )

    return authentication.register( auth )
      .addOnFailureListener {
        val text = "Invalid email or Password"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText( activity, text, duration )
        toast.show()

      }

  }

  override fun authByEmailAndPassword( email: String, password: String ): Authentication.UserFactoryParams.EmailAndPassword {
    val user = UserAuth( email, password )
    return Authentication.UserFactoryParams.EmailAndPassword( user );
  }

}