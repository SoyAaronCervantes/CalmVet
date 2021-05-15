package com.soyaaroncervantes.calmvet.models.pets

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.parcelize.Parcelize
import java.io.File
import java.util.*

@Parcelize
data class Animal(
  val name: String,
  val genre: String,
  val age: String,
  val animal: String,
  val description: String,
  var headerPhoto: Uri,
  val createdAt: Long,
  val createdBy: String?,
  val id: String
  ) : Parcelable {
  constructor(name: String, genre: String, age: String, animal: String, description: String) : this(
    name,
    genre,
    age,
    animal,
    description,
    Uri.EMPTY,
    Calendar.getInstance().timeInMillis,
    null,
    FirebaseFirestore.getInstance().collection("pets").document().id,
  )

  object Map {
    fun from(data: Animal, user: FirebaseUser) = mutableMapOf<String, Any>(
      "name" to data.name,
      "genre" to data.genre,
      "age" to data.age,
      "animal" to data.animal,
      "description" to data.description,
      "headerPhoto" to data.headerPhoto.toString(),
      "id" to data.id,
      "createdAt" to data.createdAt,
      "createdBy" to user.uid
    )
  }

  companion object {
    fun DocumentSnapshot.toAnimal(): Animal? {
      return try {
        val name = getString("name")!!
        val genre = getString("genre")!!
        val age = getString("age")!!
        val animal = getString("animal")!!
        val description = getString("description")!!
        val headerPhoto = Uri.fromFile( File( getString("headerPhoto")!! ) )
        val id = getString("id")!!
        val createdAt = getLong("createdAt")!!
        val createdBy = getString("createdBy")!!

        Animal(name, genre, age, animal, description, headerPhoto, createdAt, createdBy, id )
      } catch (e: Exception) {
        Log.e(TAG, "Error converting user profile", e)
        null
      }
    }
    private const val TAG = "User"
  }
}