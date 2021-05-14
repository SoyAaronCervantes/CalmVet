package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await

class PetViewModel : ViewModel() {
  private val mutableAnimal = MutableLiveData<Animal>()
  val animal: LiveData<Animal>
    get() = mutableAnimal

  fun setAnimal(animal: Animal) {
    mutableAnimal.value = animal
  }

  fun getAnimalAvailable(): ArrayList<String> {
    return arrayListOf("Perro", "Gato", "Cuyo", "Hurón", "Conejo", "Pato")
  }

  suspend fun saveDataOnFirestore(animal: Animal): Boolean {
    val firestore = FirebaseFirestore.getInstance()
    val hashMap = Animal.ToHashMap.from(animal)
    return try {
      val data = firestore
        .collection(PETS_COLLECTION)
        .document(animal.id)
        .set(hashMap)
        .await()
      true
    } catch (e: Exception) {
      false
    }

  }

  companion object {
    private const val PETS_COLLECTION = "pets"
  }
}