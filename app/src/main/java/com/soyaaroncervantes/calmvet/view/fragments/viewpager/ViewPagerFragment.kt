package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.FragmentViewPagerBinding
import com.soyaaroncervantes.calmvet.databinding.TablayoutBinding

class ViewPagerFragment : Fragment() {
  private lateinit var binding: FragmentViewPagerBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

    binding = FragmentViewPagerBinding.inflate(inflater, container, false)
    val view = binding.root
    val fragmentList = arrayListOf(PetsFragment(), FavoritePetsFragment())
    val adapter = ViewPagerAdapter(fragmentList, childFragmentManager, lifecycle)

    val viewPager = binding.viewPager
    val tabBar = TablayoutBinding.bind( view )

    viewPager.adapter = adapter

    tabLayoutManager(tabBar.tabLayout, viewPager)

    return view
  }

  private fun tabLayoutManager(tabLayout: TabLayout, viewPager: ViewPager2) {

    val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
      when (position) {
        0 -> tab.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_pets)
        1 -> tab.icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_favorite )
        else -> throw Throwable("Error on TabLayout, position: $position")
      }
    }

    tabLayoutMediator.attach()

  }


}