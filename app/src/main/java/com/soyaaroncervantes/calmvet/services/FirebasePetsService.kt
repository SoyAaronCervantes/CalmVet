package com.soyaaroncervantes.calmvet.services

import android.util.Log
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.soyaaroncervantes.calmvet.enums.FirestoreCollection
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await

object FirebasePetsService {
  private const val TAG = "[Firebase Pet Service]"

  fun getPets(): FirestoreRecyclerOptions<Animal> {

    val querySnapshot: Query = getCollection( FirestoreCollection.PETS.toString() )

    return FirestoreRecyclerOptions
      .Builder<Animal>()
      .setQuery(querySnapshot, Animal::class.java)
      .build()

  }
  fun getPets(user: FirebaseUser): FirestoreRecyclerOptions<Animal> {
    val collection = getCollection( FirestoreCollection.PETS.toString() )
    val querySnapshot: Query = collection.whereEqualTo("createdBy", user.uid)

    return FirestoreRecyclerOptions
      .Builder<Animal>()
      .setQuery(querySnapshot, Animal::class.java)
      .build()
  }

  suspend fun addPet(animal: Animal, user: FirebaseUser): Void? {
    val collection = getCollection( FirestoreCollection.PETS.toString() )
    animal.createdBy = user.uid
    return try {
      collection
        .document(animal.id!!)
        .set(animal)
        .await()
    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }

  private fun getCollection( collectionName: String ): CollectionReference {
    val db = FirebaseFirestore.getInstance()
    return db.collection( collectionName )
  }
}