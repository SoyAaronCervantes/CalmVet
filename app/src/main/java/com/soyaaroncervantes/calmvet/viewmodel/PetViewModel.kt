package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetViewModel: ViewModel() {
  private val mutableAnimal = MutableLiveData<Animal>()
  val animal: LiveData<Animal>
    get() = mutableAnimal

  fun setAnimal( animal: Animal ) {
    mutableAnimal.value = animal
  }

  fun getAnimalAvailable(): ArrayList<String> {
    return arrayListOf( "Perro", "Gato", "Cuyo", "Hurón", "Conejo", "Pato" )
  }

  fun save(animal: Animal) {
    val firestore = FirebaseFirestore.getInstance()
    val ref = firestore.collection(PETS_COLLECTION).document( animal.id )

  }

  companion object {
    private const val PETS_COLLECTION = "pets"
  }
}