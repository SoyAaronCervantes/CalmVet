package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.soyaaroncervantes.calmvet.databinding.FragmentFavoritePetsBinding
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class FavoritePetsFragment : Fragment() {
  private lateinit var binding: FragmentFavoritePetsBinding
  private val toolbarViewModel: ToolbarViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentFavoritePetsBinding.inflate( inflater, container, false )
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    toolbarViewModel.setTitle("Tus mascotas")
  }

}