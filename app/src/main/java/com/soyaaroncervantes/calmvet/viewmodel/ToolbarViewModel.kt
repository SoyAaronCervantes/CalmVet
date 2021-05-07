package com.soyaaroncervantes.calmvet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolbarViewModel: ViewModel() {
  private val mutableTitle = MutableLiveData<String>()
  val title: LiveData<String>
    get() = mutableTitle

  fun setTitle( title: String ) {
    mutableTitle.value = title
  }

}