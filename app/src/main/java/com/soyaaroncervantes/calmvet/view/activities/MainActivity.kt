package com.soyaaroncervantes.calmvet.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView( this, R.layout.activity_main )
  }

}