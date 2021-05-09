package com.soyaaroncervantes.calmvet.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.soyaaroncervantes.calmvet.view.fragments.AddPetFragment
import com.soyaaroncervantes.calmvet.view.fragments.PetsFragment

class ViewPagerAdapter( fragmentActivity: FragmentActivity ): FragmentStateAdapter( fragmentActivity ) {
  override fun getItemCount(): Int = 2

  override fun createFragment(position: Int): Fragment {
    return when( position ) {
      0 -> PetsFragment()
      1 -> AddPetFragment()
      else -> throw Throwable("Invalid position $position.")
    }
  } 

}