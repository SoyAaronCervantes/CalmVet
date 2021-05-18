package com.soyaaroncervantes.calmvet.models.pets

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class Animal {
  var name: String? = null
  var genre: String? = null
  var age: String? = null
  var animal: String? = null
  var description: String? = null
  private var createdAt: Long? = null
  var createdBy: String? = null
  var id: String? = null

  constructor()
  constructor(name: String, genre: String, age: String, animal: String, description: String, createdAt: Long, createdBy: String, id: String) {
    this.name = name
    this.genre = genre
    this.age = age
    this.animal = animal
    this.description = description
    this.createdAt = createdAt
    this.createdBy = createdBy
    this.id = id
  }

  constructor(name: String, genre: String, age: String, animal: String, description: String) {
    this.name = name
    this.genre = genre
    this.age = age
    this.animal = animal
    this.description = description
    this.createdAt = Calendar.getInstance().timeInMillis
    this.id = FirebaseFirestore.getInstance().collection("pets").document().id
  }

  companion object {
    fun DocumentSnapshot.toAnimal(): Animal? {

      return try {
        val name = getString("name")!!
        val genre = getString("genre")!!
        val age = getString("age")!!
        val animal = getString("animal")!!
        val description = getString("description")!!
        val id = getString("id")!!
        val createdAt = getLong("createdAt")!!
        val createdBy = getString("createdBy")!!

        Animal(name, genre, age, animal, description, createdAt, createdBy, id)
      } catch (e: Exception) {
        Log.e(TAG, "Error converting user profile", e)
        null
      }
    }

    private const val TAG = "User"
  }
}