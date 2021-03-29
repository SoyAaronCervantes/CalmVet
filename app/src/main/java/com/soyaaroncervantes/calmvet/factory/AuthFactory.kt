package com.soyaaroncervantes.calmvet.factory

/** Task */
import com.google.android.gms.tasks.Task

/** Firebase lib */
import com.google.firebase.ktx.Firebase

/** Auth libs */
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth

class AuthFactory {

  sealed class FactoryParams {
    data class User( val email: String, val password: String    ) : FactoryParams()
    data class Google( val idToken: String ) : FactoryParams()
    data class Facebook( val idToken: String ) : FactoryParams()
  }

  companion object {
    private val firebaseAuth = Firebase.auth;

    fun login(params: FactoryParams): Task<AuthResult> =
      when ( params ) {

        is FactoryParams.User -> firebaseAuth.signInWithEmailAndPassword( params.email, params.password )
        is FactoryParams.Facebook -> {
          val credential = FacebookAuthProvider.getCredential( params.idToken )
          firebaseAuth.signInWithCredential( credential )
        }
        is FactoryParams.Google -> {
          val credential = GoogleAuthProvider.getCredential( params.idToken, null )
          firebaseAuth.signInWithCredential( credential )
        }

      }

    fun register(params: FactoryParams): Task<AuthResult> =

      when (params) {

        is FactoryParams.User -> Firebase.auth.createUserWithEmailAndPassword( params.email, params.password )
        is FactoryParams.Facebook -> {
          val credential = FacebookAuthProvider.getCredential( params.idToken )
          firebaseAuth.signInWithCredential( credential )
        }
        is FactoryParams.Google -> {
          val credential = GoogleAuthProvider.getCredential( params.idToken, null )
          firebaseAuth.signInWithCredential( credential )
        }

      }
  }
}