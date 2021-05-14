package com.soyaaroncervantes.calmvet.services

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseUser
import com.soyaaroncervantes.calmvet.R

object FirebaseSignInService {

  fun launchFirebaseUISignIn( activityResultLauncher: ActivityResultLauncher<Intent>) {
    val firebaseIntent = firebaseUIConfig()
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

  private fun firebaseUIConfig(): Intent {
    return AuthUI.getInstance()
      .createSignInIntentBuilder()
      .setAvailableProviders( providers() )
      .setIsSmartLockEnabled(true)
      .setLogo( R.drawable.logo )
      .setResetPasswordSettings( ActionCodeSettings.zza() )
      .build()
  }

}