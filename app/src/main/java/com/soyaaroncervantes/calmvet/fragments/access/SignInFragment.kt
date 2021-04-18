package com.soyaaroncervantes.calmvet.fragments.access

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val response = IdpResponse.fromResultIntent(result.data)

        // Check if response isn't null
        if (response === null) { return@registerForActivityResult }

        // Check if user has Network Connection
        if ( response.error?.errorCode == ErrorCodes.NO_NETWORK ) { Toast.makeText(requireContext(), getString(R.string.noNetwork), Toast.LENGTH_LONG).show() }

        // Check if user has Network Connection
        if ( response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR ) { Toast.makeText(requireContext(), getString(R.string.unknownError), Toast.LENGTH_LONG).show() }

        // If result code is ok, we get currentUser
        if (result.resultCode == Activity.RESULT_OK) {
            val user = firebaseAuth.currentUser

            if ( user !== null ) {
                Log.d("[User]", user.uid)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        val providers = providers()
        val firebaseIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(true)
            .build()

        getContent.launch(firebaseIntent)
    }

    private fun providers(): ArrayList<AuthUI.IdpConfig> {
        return arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.FacebookBuilder().build(),
            AuthUI.IdpConfig.TwitterBuilder().build()
        )
    }
}