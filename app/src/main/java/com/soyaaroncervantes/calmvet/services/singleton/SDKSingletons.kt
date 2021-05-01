package com.soyaaroncervantes.calmvet.services.singleton

import com.soyaaroncervantes.calmvet.services.facebook.FacebookInstance
import com.soyaaroncervantes.calmvet.services.firebase.FirebaseUISignIn

object SDKSingletons {
  val firebase = FirebaseUISignIn.Companion
  val facebook = FacebookInstance.Companion
}