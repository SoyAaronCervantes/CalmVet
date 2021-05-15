package com.soyaaroncervantes.calmvet.view.fragments.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.soyaaroncervantes.calmvet.databinding.FragmentPetsBinding
import com.soyaaroncervantes.calmvet.view.adapter.PetsAdapter
import com.soyaaroncervantes.calmvet.viewmodel.PetsViewModel
import com.soyaaroncervantes.calmvet.viewmodel.ToolbarViewModel

class PetsFragment : Fragment() {
  // Binding
  private lateinit var binding: FragmentPetsBinding
  private lateinit var petsAdapter: PetsAdapter
  private val toolbarViewModel: ToolbarViewModel by activityViewModels()
  private val petsViewModel: PetsViewModel by activityViewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentPetsBinding.inflate( inflater, container, false)
    return binding.root
  }

  override fun onResume() {
    super.onResume()
    toolbarViewModel.setTitle("Mascotas")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val recyclerView = binding.recyclerViewPets
    petsAdapter = PetsAdapter()

    recyclerView.apply {
      layoutManager = LinearLayoutManager(view.context)
      adapter = petsAdapter
    }

    petsViewModel.pets.observe( viewLifecycleOwner ) {
      if ( !it.isNullOrEmpty() ) {
        petsAdapter.updateList( it )
        binding.relativeLayoutPets.visibility = View.INVISIBLE
      }
    }

  }

  companion object {
    private const val TAG = "[Pets Fragment]"
  }

}