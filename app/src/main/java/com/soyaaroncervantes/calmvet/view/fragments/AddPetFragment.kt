package com.soyaaroncervantes.calmvet.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.soyaaroncervantes.calmvet.databinding.FragmentAddPetBinding
import com.soyaaroncervantes.calmvet.viewmodel.AddPetViewModel

class AddPetFragment : Fragment() {
  private lateinit var binding: FragmentAddPetBinding
  private lateinit var addPetViewModel: AddPetViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentAddPetBinding.inflate( inflater, container, false )
    addPetViewModel = ViewModelProvider( this).get( AddPetViewModel::class.java )
    return binding.root
  }
}