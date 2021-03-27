package com.soyaaroncervantes.calmvet.factory.auth

import android.util.Log

class RegisterFactory {

  companion object {
    fun register( provider: Provider ): Int =

      when ( provider ) {
        Provider.EMAIL -> Log.d("[Email]", "Testing w/ Email" )
        Provider.FACEBOOK -> Log.d("[Facebook]", "Testing w/ FB" )
        Provider.GOOGLE -> Log.d("[Google]", "Testing w/ Google" )

      }

  }

}