package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetService
import kotlinx.coroutines.launch

class PetsViewModel: ViewModel() {
  private val _pets = MutableLiveData<List<Animal>>()
  val pets: LiveData<List<Animal>> = _pets

  init {
    viewModelScope.launch {
      _pets.value = FirebasePetService.getPets()?.toObjects(Animal::class.java)
    }
  }
}