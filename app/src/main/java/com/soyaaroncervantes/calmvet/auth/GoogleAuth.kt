package com.soyaaroncervantes.calmvet.auth

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soyaaroncervantes.calmvet.R

class GoogleAuth {
  var googleSignInClient: GoogleSignInClient? = null

  companion object {
    fun init(): GoogleSignInOptions {
      return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken( R.string.default_web_client_id.toString() )
        .requestEmail()
        .build()
    }
  }

}