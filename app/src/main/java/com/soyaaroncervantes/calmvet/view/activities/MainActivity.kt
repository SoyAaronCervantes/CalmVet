package com.soyaaroncervantes.calmvet.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val adapter by lazy { ViewPagerAdapter(this) }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView( this, R.layout.activity_main )
    val viewPager = binding.viewPager
    val tabLayout = binding.tabLayout

    viewPager.adapter = adapter
    val tabLayoutMediator = TabLayoutMediator( tabLayout, viewPager) { tab, position ->
      when (position) {
        0 -> tab.icon = AppCompatResources.getDrawable( this, R.drawable.ic_pets )
        else -> tab.icon = AppCompatResources.getDrawable( this, R.drawable.ic_add )
      }
    }
    tabLayoutMediator.attach()
  }

}