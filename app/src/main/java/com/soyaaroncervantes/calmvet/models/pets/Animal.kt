package com.soyaaroncervantes.calmvet.models.pets

data class Animal(
  val name: String,
  val genre: String,
  val age: String,
  val animal: String,
  val description: String,
  val iconPhoto: String,
  val headerPhoto: String,
  val id: String
) {
  constructor() : this("", "", "", "", "", "", "", "")
  constructor( name: String, genre: String, age: String, animal: String, description: String ): this( name,genre, age, animal, description, "", "", "" )
}