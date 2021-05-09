package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.soyaaroncervantes.calmvet.databinding.FragmentPetsBinding

class PetsFragment : Fragment() {
  // Binding
  private lateinit var binding: FragmentPetsBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentPetsBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val recyclerView = binding.recyclerViewPets
    recyclerView.apply { layoutManager = LinearLayoutManager(view.context) }
  }

}