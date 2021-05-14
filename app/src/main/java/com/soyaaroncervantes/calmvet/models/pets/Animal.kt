package com.soyaaroncervantes.calmvet.models.pets

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

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
  ) {
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
      "headerPhoto" to data.headerPhoto,
      "id" to data.id,
      "createdAt" to data.createdAt,
      "createdBy" to user.uid
    )
  }
}