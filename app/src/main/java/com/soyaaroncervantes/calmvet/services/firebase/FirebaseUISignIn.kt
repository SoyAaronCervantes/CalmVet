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
// Firebase Firestore Port
const val FIRESTORE_PORT = 8080
// Firebase Auth Port
const val AUTH_PORT = 9099;

class FirebaseUISignIn {
  lateinit var firebaseAuth: FirebaseAuth
  private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

  companion object {
    lateinit var user: FirebaseUser

    init {
      // Create Firebase Firestore instance
      val authUI = AuthUI.getInstance()
      // Set Firebase Emulators
      authUI.useEmulator( HOST_PORT, FIRESTORE_PORT );
      authUI.useEmulator( HOST_PORT, AUTH_PORT );
    }
  }

  fun launchFirebaseUISignIn( activityResultLauncher: ActivityResultLauncher<Intent> ) {

    this.activityResultLauncher = activityResultLauncher

    val providers = providers()
    val firebaseIntent = AuthUI.getInstance()
      .createSignInIntentBuilder()
      .setAvailableProviders(providers)
      .setIsSmartLockEnabled(true)
      .setLogo( R.drawable.logo )
      .setResetPasswordSettings( ActionCodeSettings.zza() )
      .build()

    this.activityResultLauncher.launch(firebaseIntent)
  }

  fun validateResult( result: ActivityResult, context: Context ) {
    val response = IdpResponse.fromResultIntent(result.data)

    // Check if response isn't null
    if (response === null) { return }

    // Check if user has Network Connection
    if ( response.error?.errorCode == ErrorCodes.NO_NETWORK ) { Toast.makeText( context, context.getString(R.string.noNetwork), Toast.LENGTH_LONG).show() }

    // Check if user has Network Connection
    if ( response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR ) { Toast.makeText( context, context.getString(R.string.unknownError), Toast.LENGTH_LONG).show() }

    // If result code is ok, we get currentUser

    if (result.resultCode == Activity.RESULT_OK) {

      val user = firebaseAuth.currentUser

      if ( user !== null ) { FirebaseUISignIn.user = firebaseAuth.currentUser!! }

    }

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