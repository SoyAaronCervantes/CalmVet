package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.services.FirebasePetsService
import kotlinx.coroutines.launch

class PetsViewModel: ViewModel() {
  private val _pets = MutableLiveData<List<Animal>>()
  val pets: LiveData<List<Animal>> = _pets

  private val _userPets = MutableLiveData<List<Animal>>()
  val userPets: LiveData<List<Animal>> = _userPets

  private val _user = MutableLiveData<FirebaseUser>()
  private val user: LiveData<FirebaseUser> = _user

  init {
    viewModelScope.launch {
      _user.value = UserViewModel().userProfile.value
      _pets.value = FirebasePetsService.getPets()
      _pets.value  = user.value?.let { FirebasePetsService.getPetsFromUser(it) }
    }
  }

}