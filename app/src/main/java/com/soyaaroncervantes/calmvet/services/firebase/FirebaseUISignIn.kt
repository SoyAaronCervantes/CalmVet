package com.soyaaroncervantes.calmvet.services.firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.R

// Firebase Host Port
const val HOST_PORT = "10.0.2.2"
// Firebase FirestoreAPI Port
const val FIRESTORE_PORT = 8080
// Firebase Auth Port
const val AUTH_PORT = 9099;

class FirebaseUISignIn {

  companion object {
    lateinit var user: FirebaseUser

    init {
      // Create Firebase FirestoreAPI instance
      val authUI = AuthUI.getInstance()
      // Set Firebase Emulators
      authUI.useEmulator( HOST_PORT, FIRESTORE_PORT );
      authUI.useEmulator( HOST_PORT, AUTH_PORT );
    }
  }

  fun launchFirebaseUISignIn( activityResultLauncher: ActivityResultLauncher<Intent>) {

    val providers = providers()
    val firebaseIntent = AuthUI.getInstance()
      .createSignInIntentBuilder()
      .setAvailableProviders(providers)
      .setIsSmartLockEnabled(true)
      .setLogo( R.drawable.logo )
      .setResetPasswordSettings( ActionCodeSettings.zza() )
      .build()

    activityResultLauncher.launch(firebaseIntent)
  }

  private fun providers(): ArrayList<AuthUI.IdpConfig> {

    return arrayListOf(
      AuthUI.IdpConfig.EmailBuilder().build(),
      AuthUI.IdpConfig.PhoneBuilder().build(),
      AuthUI.IdpConfig.GoogleBuilder().build(),
      AuthUI.IdpConfig.FacebookBuilder().build(),
      AuthUI.IdpConfig.TwitterBuilder().build()
    )

  }

}