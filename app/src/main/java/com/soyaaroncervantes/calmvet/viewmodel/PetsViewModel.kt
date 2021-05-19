package com.soyaaroncervantes.calmvet.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetsService
import kotlinx.coroutines.launch

class PetsViewModel: ViewModel() {

  private val mutablePetPhoto = MutableLiveData<Uri>()
  val petPhoto: LiveData<Uri> = mutablePetPhoto

  fun pets(): FirestoreRecyclerOptions<Animal> = FirebasePetsService.getPets()
  fun pets( user: FirebaseUser ): FirestoreRecyclerOptions<Animal> = FirebasePetsService.getPets( user )

  fun photo( animal: Animal, user: FirebaseUser ) {
    viewModelScope.launch {
      mutablePetPhoto.value = FirebasePetsService.getPetPhoto( animal, user )
    }
  }

}