package com.soyaaroncervantes.calmvet.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.ActivityMainBinding
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate( layoutInflater )
    setContentView(binding.root)

  }


}