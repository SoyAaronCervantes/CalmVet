package com.soyaaroncervantes.calmvet.services

import android.util.Log
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.soyaaroncervantes.calmvet.enums.FirestoreCollection
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await

object FirebasePetsService {
  private const val TAG = "[Firebase Pet Service]"

  fun getPets(): FirestoreRecyclerOptions<Animal> {

    val db = FirebaseFirestore.getInstance()
    val collection = db.collection(FirestoreCollection.PETS.toString())
    val querySnapshot: Query = collection
    return FirestoreRecyclerOptions
      .Builder<Animal>()
      .setQuery(querySnapshot, Animal::class.java)
      .build()

  }

  fun getPets(user: FirebaseUser): FirestoreRecyclerOptions<Animal> {
    val db = FirebaseFirestore.getInstance()
    val collection = db.collection(FirestoreCollection.PETS.toString())
    val querySnapshot: Query = collection.whereEqualTo("createdBy", user.uid)
    return FirestoreRecyclerOptions
      .Builder<Animal>()
      .setQuery(querySnapshot, Animal::class.java)
      .build()
  }

  suspend fun addPet(animal: Animal, user: FirebaseUser): Void? {
    val db = FirebaseFirestore.getInstance()
    val collectionReference = db.collection(FirestoreCollection.PETS.toString())
    animal.createdBy = user.uid
    return try {
      collectionReference
        .document(animal.id!!)
        .set(animal)
        .await()
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }

}