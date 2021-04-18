package com.soyaaroncervantes.calmvet.firebase

import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

// Firebase Host Port
const val HOST_PORT = "10.0.2.2"
// Firebase Firestore Port
const val FIRESTORE_PORT = 8080
// Firebase Auth Port
const val AUTH_PORT = 9099;

class FirebaseInstance {

  companion object {
    init {
      // Create Firebase Firestore instance
      val authUI = AuthUI.getInstance()

      // Set Firebase Emulators
      authUI.useEmulator( HOST_PORT, FIRESTORE_PORT );
      authUI.useEmulator( HOST_PORT, AUTH_PORT );

    }
  }

}