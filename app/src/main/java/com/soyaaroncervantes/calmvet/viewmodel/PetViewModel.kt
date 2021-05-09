package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetViewModel: ViewModel() {
  private val mutableAnimal = MutableLiveData<Animal>()
  val animal: LiveData<Animal>
    get() = mutableAnimal

  fun setAnimal( animal: Animal ) {
    mutableAnimal.value = animal
  }
}