package com.soyaaroncervantes.calmvet.services

import android.content.Context
import android.widget.Toast

class ToastManager {
  companion object {
    fun displayToast(context: Context, message: String) {
      val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
      toast.show()
    }
  }
}