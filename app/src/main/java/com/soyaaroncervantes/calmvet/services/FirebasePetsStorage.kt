package com.soyaaroncervantes.calmvet.services

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.soyaaroncervantes.calmvet.models.pets.Animal
import kotlinx.coroutines.tasks.await
import java.io.File
import java.io.FileInputStream

object FirebasePetsStorage {
  private const val TAG = "[Firebase Storage Pet Service]"
  private const val PATH_PREFIX = "images/pets"

  suspend fun addPhoto( animal: Animal, file: File ): UploadTask.TaskSnapshot? {

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    val path = "$PATH_PREFIX/${animal.id}"
    val imageRef = storageRef.child(path)

    return try {

      imageRef
        .putFile( Uri.fromFile( file ) )
        .await()

    } catch (e: Exception) {
      Log.e(TAG, "${e.message}")
      null
    }
  }
}