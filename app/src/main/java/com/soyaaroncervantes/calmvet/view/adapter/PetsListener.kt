package com.soyaaroncervantes.calmvet.view.adapter

import com.soyaaroncervantes.calmvet.models.pets.Animal

interface PetsListener {
  fun onPetClick( pet: Animal, position: Int )
}