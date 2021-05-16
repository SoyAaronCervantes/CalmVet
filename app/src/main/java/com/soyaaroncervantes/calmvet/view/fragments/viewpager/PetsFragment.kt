package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.soyaaroncervantes.calmvet.databinding.FragmentPetsBinding
import com.soyaaroncervantes.calmvet.view.adapter.PetsAdapter
import com.soyaaroncervantes.calmvet.viewmodel.PetsViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class PetsFragment : Fragment() {
  // Binding
  private val toolbarViewModel: ToolbarViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val binding = FragmentPetsBinding.inflate( inflater, container, false)
    val petsViewModel = ViewModelProvider( this )[ PetsViewModel::class.java ]
    val petsAdapter = PetsAdapter()

    val recyclerView = binding.recyclerViewPets
    recyclerView.apply {
      layoutManager = LinearLayoutManager( view?.context )
      adapter = petsAdapter
    }

    petsViewModel.pets.observe( viewLifecycleOwner ) {
      if ( !it.isNullOrEmpty() ) {
        petsAdapter.updateList( it )
        binding.relativeLayoutPets.visibility = View.INVISIBLE
      }
    }

    return binding.root

  }

  override fun onResume() {
    super.onResume()
    toolbarViewModel.setTitle("Mascotas")
  }

}