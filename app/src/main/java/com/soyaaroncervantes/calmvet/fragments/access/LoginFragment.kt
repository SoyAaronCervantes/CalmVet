package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.factory.auth.LoginFactory
import com.soyaaroncervantes.calmvet.factory.auth.Provider

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton: MaterialButton = view.findViewById( R.id.registerButton )

        val googleButton: MaterialButton = view.findViewById( R.id.googleButton )
        val facebookButton: MaterialButton = view.findViewById( R.id.facebookButton )

        /** toDo Implement Factory Pattern for Login & Auth */


        googleButton.setOnClickListener {
            val provider = Provider.GOOGLE
            LoginFactory.login( provider )
        }

        facebookButton.setOnClickListener {
            val provider = Provider.FACEBOOK
            LoginFactory.login( provider )
        }

    }

}