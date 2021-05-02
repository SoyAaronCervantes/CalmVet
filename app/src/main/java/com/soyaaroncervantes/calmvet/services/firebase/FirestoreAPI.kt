package com.soyaaroncervantes.calmvet.services.firebase

import com.google.firebase.storage.FirebaseStorage

class FirestoreAPI {
  private val firebaseStorage = FirebaseStorage.getInstance()
  private val storageRef = firebaseStorage.reference
}