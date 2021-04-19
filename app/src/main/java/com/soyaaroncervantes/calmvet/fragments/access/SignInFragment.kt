package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.soyaaroncervantes.calmvet.databinding.FragmentSignInBinding
import com.soyaaroncervantes.calmvet.firebase.FirebaseUISignIn

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseUISignIn: FirebaseUISignIn
    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> firebaseUISignIn.validateResult( result, requireContext() )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUISignIn = FirebaseUISignIn()
        firebaseUISignIn.firebaseAuth = firebaseAuth
        firebaseUISignIn.launchFirebaseUISignIn( getContent )
    }

}