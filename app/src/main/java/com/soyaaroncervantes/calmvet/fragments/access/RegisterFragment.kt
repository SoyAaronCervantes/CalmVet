package com.soyaaroncervantes.calmvet.fragments.access

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.soyaaroncervantes.calmvet.R

class RegisterFragment : Fragment() {
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val loginButton: MaterialButton = view.findViewById( R.id.loginButton )

        val googleButton: MaterialButton = view.findViewById( R.id.googleButton )
        val facebookButton: MaterialButton = view.findViewById( R.id.facebookButton )

        /** toDo Implement Factory Pattern for Login & Auth */

    }
}