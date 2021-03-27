package com.soyaaroncervantes.calmvet.models.pets

class Dog (
  name: String,
  id: String,
  genre: String,
  age: String,
  val breed: String
): Animal ( name, id, genre, age )
