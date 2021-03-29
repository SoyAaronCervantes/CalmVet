package com.soyaaroncervantes.calmvet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.auth.GoogleAuth
import com.soyaaroncervantes.calmvet.firebase.FirebaseInstance

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Init Firebase Emulator
    FirebaseInstance.init();
    val googleSignInOptions = GoogleAuth.init()
    val googleAuth = GoogleAuth()
    googleAuth.googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions )
  }
}