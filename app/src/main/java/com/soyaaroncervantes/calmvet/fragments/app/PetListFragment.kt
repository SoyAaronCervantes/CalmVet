package com.soyaaroncervantes.calmvet.fragments.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentPetListBinding
import com.soyaaroncervantes.calmvet.firebase.FirebaseUISignIn
import com.soyaaroncervantes.calmvet.fragments.app.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class PetListFragment : Fragment() {
  private lateinit var binding: FragmentPetListBinding
  private lateinit var firebaseAuth: FirebaseAuth
  private lateinit var firebaseUISignIn: FirebaseUISignIn
  private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
      result: ActivityResult -> firebaseUISignIn.validateResult( result, requireContext() )
  }

  private var columnCount = 1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      columnCount = it.getInt(ARG_COLUMN_COUNT)
    }
  }

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    binding = FragmentPetListBinding.inflate( inflater, container, false )
    val recyclerView = binding.root

    firebaseAuth = FirebaseAuth.getInstance()
    firebaseUISignIn = FirebaseUISignIn()
    firebaseUISignIn.firebaseAuth = firebaseAuth
    firebaseUISignIn.launchFirebaseUISignIn( getContent )

    // Set the adapter
    with(recyclerView) {
      layoutManager = when {
        columnCount <= 1 -> LinearLayoutManager(context)
        else -> GridLayoutManager(context, columnCount)
      }
      adapter = MyPetListRecyclerViewAdapter(DummyContent.ITEMS)
    }

    return recyclerView
  }

  companion object {

    // TODO: Customize parameter argument names
    const val ARG_COLUMN_COUNT = "column-count"

    // TODO: Customize parameter initialization
    @JvmStatic
    fun newInstance(columnCount: Int) =
      PetListFragment().apply {
        arguments = Bundle().apply {
          putInt(ARG_COLUMN_COUNT, columnCount)
        }
      }
  }
}