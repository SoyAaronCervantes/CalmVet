package com.soyaaroncervantes.calmvet.interfaces

import com.soyaaroncervantes.calmvet.auth.Authentication

interface FirebaseAuthMethods {
  fun authByEmailAndPassword( email: String, password: String ): Authentication.UserFactoryParams.EmailAndPassword
}