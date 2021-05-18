package com.soyaaroncervantes.calmvet.services

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import com.soyaaroncervantes.calmvet.enums.FirestoreCollection
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await

object FirebaseUserService {
  private const val TAG = "[Firebase User Service]"

  suspend fun getUser(): FirebaseUser? {
    val auth = FirebaseAuth.getInstance()
    return try {
      auth.currentUser
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }
}