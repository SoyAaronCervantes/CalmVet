package com.soyaaroncervantes.calmvet.auth

/** Task */
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*

/** Firebase lib */
import com.google.firebase.ktx.Firebase

/** Auth libs */
import com.google.firebase.auth.ktx.auth
import com.soyaaroncervantes.calmvet.models.user.UserAuth

class Authentication( private val firebaseAuth: FirebaseAuth ) {

  sealed class UserFactoryParams {
    data class EmailAndPassword( val userAuth: UserAuth ) : UserFactoryParams()
  }

  fun login( params: UserFactoryParams ): Task<AuthResult> = when ( params ) {
      is UserFactoryParams.EmailAndPassword -> firebaseAuth.signInWithEmailAndPassword( params.userAuth.email, params.userAuth.password )
    }

  fun register(params: UserFactoryParams): Task<AuthResult> = when (params) {
      is UserFactoryParams.EmailAndPassword -> Firebase.auth.createUserWithEmailAndPassword( params.userAuth.email, params.userAuth.password )
    }

}