package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetsViewModel: ViewModel() {
  val listSchedule: MutableLiveData< List<Animal> > = MutableLiveData()
  var isLoading = MutableLiveData<Boolean>()

  fun refresh() {
    loadPetList()
  }

  private fun loadPetList() {

  }

  private fun processFinished() {
    isLoading.value = true
  }
}