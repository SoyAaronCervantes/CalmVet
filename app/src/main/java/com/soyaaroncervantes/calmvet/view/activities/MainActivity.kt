package com.soyaaroncervantes.calmvet.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.ActivityMainBinding
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val toolbarViewModel: ToolbarViewModel by viewModels()
  private val adapter by lazy { ViewPagerAdapter(this) }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView( this, R.layout.activity_main )
    val viewPager = binding.viewPager
    val tabLayout = binding.tabLayout
    val topAppBar = binding.topAppBar

    viewPager.adapter = adapter

    toolbarViewModel.title.observe( this, { topAppBar.title = it })
    tabLayoutManager( tabLayout, viewPager )

  }

  private fun tabLayoutManager( tabLayout: TabLayout, viewPager: ViewPager2 ) {
    val tabLayoutMediator = TabLayoutMediator( tabLayout, viewPager) { tab, position ->
      when (position) {
        0 -> tab.icon = AppCompatResources.getDrawable( this, R.drawable.ic_pets )
        else -> tab.icon = AppCompatResources.getDrawable( this, R.drawable.ic_add )
      }
    }

    tabLayoutMediator.attach()

  }

}