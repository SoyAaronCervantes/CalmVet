package com.soyaaroncervantes.calmvet.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.soyaaroncervantes.calmvet.databinding.FragmentPetsBinding
import com.soyaaroncervantes.calmvet.services.firebase.FirebaseUISignIn
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.view.adapter.PetsAdapter
import com.soyaaroncervantes.calmvet.view.adapter.PetsListener
import com.soyaaroncervantes.calmvet.viewmodel.PetsViewModel

class PetsFragment : Fragment(), PetsListener {
  // Binding
  private lateinit var binding: FragmentPetsBinding
  // FirebaseUI & Firebase Auth
  private lateinit var firebaseAuth: FirebaseAuth
  private lateinit var firebaseUISignIn: FirebaseUISignIn
  // ViewModel & Adapter
  private lateinit var petsAdapter: PetsAdapter
  private lateinit var petsViewModel: PetsViewModel

  private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      result: ActivityResult -> firebaseUISignIn.validateResult( result, requireContext() )
  }

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    binding = FragmentPetsBinding.inflate( inflater, container, false )
    val recyclerView = binding.root

    firebaseAuth = FirebaseAuth.getInstance()
    firebaseUISignIn = FirebaseUISignIn()
    firebaseUISignIn.firebaseAuth = firebaseAuth
    firebaseUISignIn.launchFirebaseUISignIn( getContent )

    return recyclerView

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    petsViewModel = ViewModelProvider( this ).get( PetsViewModel::class.java )
    petsViewModel.refresh()

    petsAdapter = PetsAdapter(this)

  }

  override fun onPetClick(pet: Animal, position: Int) { }

}