package com.soyaaroncervantes.calmvet.factory.auth

import android.util.Log

class AuthFactory {

  sealed class FactoryParams {
    data class User(val email: String, val password: String) : FactoryParams()
    object Facebook : FactoryParams()
    object Google : FactoryParams()
  }

  companion object {
    fun login(params: FactoryParams): Int =
      when (params) {

        is FactoryParams.User -> {
          val email = params.email;
          val password = params.password
          Log.d("[EMAIL]", "$email & $password")
        }

        is FactoryParams.Facebook -> Log.d("[DEBUG]", "Log In w/ Facebook")
        is FactoryParams.Google -> Log.d("[DEBUG]", "Log In w/ Google")

      }

    fun register(params: FactoryParams): Int =
      when (params) {

        is FactoryParams.User -> {
          val email = params.email;
          val password = params.password
          Log.d("[EMAIL]", "$email & $password")
        }

        is FactoryParams.Facebook -> Log.d("[DEBUG]", "Log In w/ Facebook")
        is FactoryParams.Google -> Log.d("[DEBUG]", "Log In w/ Google")

      }
  }
}