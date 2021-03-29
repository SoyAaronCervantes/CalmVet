package com.soyaaroncervantes.calmvet.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

// Firebase Host Port
const val HOST_PORT = "10.0.2.2"
// Firebase Firestore Port
const val FIRESTORE_PORT = 8080;
// Firebase Auth Port
const val AUTH_PORT = 9099;

class FirebaseInstance {

  companion object {
    fun init() {
      // Create Firebase Firestore instance
      val firestore = FirebaseFirestore.getInstance();

      // Set Firebase Emulators
      firestore.useEmulator( HOST_PORT, FIRESTORE_PORT );
      firestore.useEmulator( HOST_PORT, AUTH_PORT );

      // Disable persistence data.
      val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(false)
        .build();

      // Set firestore settings
      firestore.firestoreSettings = settings
    }

  }

}