package com.soyaaroncervantes.calmvet.services.singleton

import com.facebook.FacebookSdk
import com.soyaaroncervantes.calmvet.services.firebase.FirebaseUISignIn

object SDKSingletons {
  val firebase = FirebaseUISignIn.Companion
  val facebook = FacebookSdk.fullyInitialize();
}