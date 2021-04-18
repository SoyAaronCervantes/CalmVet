package com.soyaaroncervantes.calmvet.singleton

import com.soyaaroncervantes.calmvet.facebook.FacebookInstance
import com.soyaaroncervantes.calmvet.firebase.FirebaseInstance

object SDKSingletons {
  val firebase = FirebaseInstance.Companion
  val facebook = FacebookInstance.Companion
}