package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetService
import com.soyaaroncervantes.calmvet.services.ToastManager
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PetViewModel : ViewModel() {
  private val mutableAnimal = MutableLiveData<Animal>()
  val animal: LiveData<Animal> = mutableAnimal

  fun setAnimal(animal: Animal) {
    mutableAnimal.value = animal
  }

  fun getAnimalAvailable(): ArrayList<String> {
    return arrayListOf("Perro", "Gato", "Cuyo", "Hurón", "Conejo", "Pato")
  }

  fun addPet(animal: Animal, user: FirebaseUser ) {
    viewModelScope.launch {
      FirebasePetService.addPet( animal, user )
    }
  }

}