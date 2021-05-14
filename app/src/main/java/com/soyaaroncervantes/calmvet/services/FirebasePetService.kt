package com.soyaaroncervantes.calmvet.services

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.soyaaroncervantes.calmvet.enums.FirestoreCollection
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await
import java.util.*

object FirebasePetService {
  private const val TAG = "[Firebase Pet Service]"

  suspend fun getPets(): QuerySnapshot? {
    val db = FirebaseFirestore.getInstance()
    return try {
      db
        .collection(FirestoreCollection.PETS.toString())
        .get()
        .await()
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }
  suspend fun getPetsFromUser( user: FirebaseUser ): QuerySnapshot?  {
    val db = FirebaseFirestore.getInstance()
    return try {
      db
        .collection(FirestoreCollection.PETS.toString())
        .whereEqualTo("createdBy", user.uid )
        .get()
        .await()
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }

  suspend fun addPet(animal: Animal, user: FirebaseUser): Void? {
    val db = FirebaseFirestore.getInstance()
    val value = Animal.Map.from(animal, user)
    return try {
      db
        .collection(FirestoreCollection.PETS.toString())
        .document(animal.id)
        .set( value )
        .await()
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }

}