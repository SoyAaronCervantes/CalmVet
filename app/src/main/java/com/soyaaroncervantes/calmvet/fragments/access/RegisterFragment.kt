package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
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


    loginButton.setOnClickListener {}

    submitButton.setOnClickListener {
      val email = emailInput.text.toString()
      val password = passwordInput.text.toString()

    }

  }

}