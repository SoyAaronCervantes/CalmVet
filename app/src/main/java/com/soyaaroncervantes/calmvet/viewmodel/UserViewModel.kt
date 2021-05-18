package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.services.FirebaseUserService
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
  private val _userProfile = MutableLiveData<FirebaseUser>()
  val userProfile: LiveData<FirebaseUser> = _userProfile

  init {
    viewModelScope.launch {
      _userProfile.value = FirebaseUserService.getUser()
    }
  }

}