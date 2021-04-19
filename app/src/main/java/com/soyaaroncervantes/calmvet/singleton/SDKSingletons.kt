package com.soyaaroncervantes.calmvet.singleton

import com.soyaaroncervantes.calmvet.facebook.FacebookInstance
import com.soyaaroncervantes.calmvet.firebase.FirebaseUISignIn

object SDKSingletons {
  val firebase = FirebaseUISignIn.Companion
  val facebook = FacebookInstance.Companion
}