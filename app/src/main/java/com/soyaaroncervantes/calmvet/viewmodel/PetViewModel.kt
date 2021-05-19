package com.soyaaroncervantes.calmvet.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetsService
import com.soyaaroncervantes.calmvet.services.FirebasePetsStorage
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

class PetViewModel : ViewModel() {
  private val mutableAnimal = MutableLiveData<Animal>()
  val animal: LiveData<Animal> = mutableAnimal

  fun setAnimal(animal: Animal) {
    mutableAnimal.value = animal
  }

  fun getAnimalAvailable(): ArrayList<String> {
    return arrayListOf("Perro", "Gato", "Cuyo", "Hurón", "Conejo", "Pato")
  }

  fun addPet(animal: Animal, user: FirebaseUser) {
    viewModelScope.launch {
      Log.d("$TAG - Add Pet", "${user.uid}: $animal")
      FirebasePetsService.addPet(animal, user)
    }
  }

  fun addPet( animal: Animal, user: FirebaseUser, file: File ) {
    viewModelScope.launch {
      Log.d("$TAG - Add Pet & Photo", "${user.uid}: $animal")

      FirebasePetsService.addPet(animal, user)
      FirebasePetsStorage.addPhoto(animal, file )
    }
  }

  companion object {
    private const val TAG = "[ Pet ViewModel ]"
  }
}