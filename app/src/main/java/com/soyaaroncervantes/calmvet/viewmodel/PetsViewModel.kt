package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.ViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetsService

class PetsViewModel: ViewModel() {
  fun pets(): FirestoreRecyclerOptions<Animal> = FirebasePetsService.getPets()
  fun pets( user: FirebaseUser ): FirestoreRecyclerOptions<Animal> = FirebasePetsService.getPets( user )
}