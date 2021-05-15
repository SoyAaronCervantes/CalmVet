package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.view.adapter.ViewPagerAdapter
import com.soyaaroncervantes.calmvet.databinding.FragmentViewPagerBinding
import com.soyaaroncervantes.calmvet.databinding.TablayoutBinding
import com.soyaaroncervantes.calmvet.databinding.ToolbarBinding
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class ViewPagerFragment : Fragment() {
  private lateinit var binding: FragmentViewPagerBinding
  private val toolbarViewModel: ToolbarViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

    binding = FragmentViewPagerBinding.inflate(inflater, container, false)
    val view = binding.root
    val fragmentList = arrayListOf(PetsFragment(), FavoritePetsFragment())
    val adapter = ViewPagerAdapter(fragmentList, childFragmentManager, lifecycle)

    val viewPager = binding.viewPager
    val tabBar = TablayoutBinding.bind( view )
    val toolbar = ToolbarBinding.bind( view )

    viewPager.adapter = adapter
    toolbarViewModel.title.observe( viewLifecycleOwner) { toolbar.topAppBar.title = it }
    tabLayoutManager(tabBar.tabLayout, viewPager)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val addPetButton = binding.addPet

    addPetButton.setOnClickListener {
      findNavController().navigate(R.id.action_viewPagerFragment_to_addPetFragment)
    }

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