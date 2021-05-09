package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soyaaroncervantes.calmvet.databinding.FragmentFavoritePetsBinding

class FavoritePetsFragment : Fragment() {
  private lateinit var binding: FragmentFavoritePetsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentFavoritePetsBinding.inflate( inflater, container, false )
    return binding.root
  }

}