package com.soyaaroncervantes.calmvet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.singleton.FirebaseSingletons

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Init Firebase Emulator
    FirebaseSingletons.firebase
  }
}