package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser

class UserProfileViewModel( savedStateHandle: SavedStateHandle ): ViewModel() {
  val userID: String = savedStateHandle["uid"] ?: throw IllegalArgumentException("Missing User ID")
  val user: LiveData<FirebaseUser> = TODO()
}