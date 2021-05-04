package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.livedata.FirebaseUserLiveData

class LoginViewModel(): ViewModel() {
  private var firebaseUserLiveData = FirebaseUserLiveData()
  private var firebaseUser: MutableLiveData<FirebaseUser> = MutableLiveData()

  val authenticationState = firebaseUserLiveData.map { user ->
    if ( user != null ) { firebaseUser.value = user }
    return@map user
  }

}