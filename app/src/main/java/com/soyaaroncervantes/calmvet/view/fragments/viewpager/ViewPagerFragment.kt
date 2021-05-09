package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
  private lateinit var binding: FragmentViewPagerBinding

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {

    binding = FragmentViewPagerBinding.inflate( inflater, container, false )
    val fragmentList = arrayListOf( PetsFragment(), FavoritePetsFragment() )
    val adapter = ViewPagerAdapter( fragmentList, childFragmentManager, lifecycle )
    val viewPager =  binding.viewPager
    viewPager.adapter = adapter
    tabLayoutManager( binding.tabLayout, viewPager )

    return binding.root
  }

  private fun tabLayoutManager( tabLayout: TabLayout, viewPager: ViewPager2 ) {

    val tabLayoutMediator = TabLayoutMediator( tabLayout, viewPager) { tab, position ->
      when (position) {
        0 -> tab.icon = AppCompatResources.getDrawable( requireContext(), R.drawable.ic_pets )
        else -> tab.icon = AppCompatResources.getDrawable( requireContext(), R.drawable.ic_add )
      }
    }

    tabLayoutMediator.attach()

  }


}