package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentPersonalDataBinding
import com.soyaaroncervantes.calmvet.models.user.Profile

class PersonalDataFragment : Fragment() {
  private lateinit var binding: FragmentPersonalDataBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val binding: FragmentPersonalDataBinding =
      DataBindingUtil.inflate(inflater, R.layout.fragment_personal_data, container, false)
    this.binding = binding
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    formFunctionality()
  }

  private fun formFunctionality() {
    val nameInput = binding.nameInput
    val nameText = nameInput.text

    val phoneInput = binding.nameInput
    val phoneText = phoneInput.text

    val submitButton = binding.submitPersonalData

    submitButton.setOnClickListener {
      if (!nameText.isNullOrEmpty() && !phoneText.isNullOrEmpty()) {
        // toDo Get user info, and send it to firebase

        val uidMockUp = "this is a test"
        val name = nameText.toString()
        val phone = phoneText.toString().toInt()
        val profile =  Profile( uidMockUp, name, phone )

        Log.d("[DEBUG]", "We need to send Profile: $profile")
      }
    }
  }
}