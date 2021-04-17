package com.soyaaroncervantes.calmvet.models.user

import com.soyaaroncervantes.calmvet.interfaces.FirebaseAuthMethods

data class UserAuth(val email: String, val password: String ): User( email )
