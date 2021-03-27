package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.factory.auth.AuthFactory

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Social Media Buttons */
        val googleButton: MaterialButton = view.findViewById( R.id.googleButton )
        val facebookButton: MaterialButton = view.findViewById( R.id.facebookButton )

        /** Register Button */
        val registerButton: MaterialButton = view.findViewById( R.id.registerButton )

        /** Submit Button */
        val submitButton: MaterialButton = view.findViewById( R.id.submitButton )

        submitButton.setOnClickListener {
            val user = AuthFactory.FactoryParams.User("asd@asd.com", "asdqwe123");
            AuthFactory.login( user )
        }

        googleButton.setOnClickListener {
            val user = AuthFactory.FactoryParams.Google
            AuthFactory.login( user )
        }

        facebookButton.setOnClickListener {
            val user = AuthFactory.FactoryParams.Facebook
            AuthFactory.login( user )
        }

        registerButton.setOnClickListener {

        }



    }

}