package com.soyaaroncervantes.calmvet.services

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.soyaaroncervantes.calmvet.enums.FirestoreCollection
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await

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
      Log.e("[ Error getting Pets ]", "${e.message}")
      null
    }
  }
  suspend fun getPetsFromUser( user: FirebaseUser ) {

  }
  suspend fun addPetToUser( animal: Animal, user: FirebaseUser ): Void? {
    val db = FirebaseFirestore.getInstance()
    return try {
      db
        .collection(FirestoreCollection.USERS.toString())
        .document(user.uid)
        .set(Animal.ToHashMap.from(animal))
        .await()
    } catch (e: Exception) {
      Log.e("[ Error adding Pets to User ]", "${e.message}")
      null
    }
  }
  suspend fun addPet(animal: Animal): Void? {
    val db = FirebaseFirestore.getInstance()
    return try {
      db
        .collection(FirestoreCollection.PETS.toString())
        .document(animal.id)
        .set(Animal.ToHashMap.from(animal))
        .await()
    } catch (e: Exception) {
      Log.e("[ Error adding Pets ]", "${e.message}")
      null
    }
  }
}