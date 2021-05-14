package com.soyaaroncervantes.calmvet.models.pets

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore

data class Animal(
  val name: String,
  val genre: String,
  val age: String,
  val animal: String,
  val description: String,
  var headerPhoto: Uri,
  val id: String
) {
  constructor() : this("", "", "", "", "", Uri.EMPTY, "")
  constructor(name: String, genre: String, age: String, animal: String, description: String) : this(
    name,
    genre,
    age,
    animal,
    description,
    Uri.EMPTY,
    FirebaseFirestore.getInstance().collection("pets").document().id
  )

  object ToHashMap {
    fun from(data: Animal) = mapOf("name" to data.name, "genre" to data.genre, "age" to data.age, "animal" to data.animal, "description" to data.description)
  }
}