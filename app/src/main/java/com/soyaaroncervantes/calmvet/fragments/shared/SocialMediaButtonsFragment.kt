package com.soyaaroncervantes.calmvet.fragments.shared

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.factory.AuthFactory

class SocialMediaButtonsFragment : Fragment() {

  override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_social_media_buttons, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    /** Social Media Buttons */
    val googleButton: MaterialButton = view.findViewById( R.id.googleButton )
    val facebookButton: MaterialButton = view.findViewById( R.id.facebookButton )

    googleButton.setOnClickListener {
      val token = "asdasd"
      val user = AuthFactory.FactoryParams.Google( token )
      AuthFactory.login( user )
    }

    facebookButton.setOnClickListener {
      val token = "asdasd"
      val user = AuthFactory.FactoryParams.Facebook( token )
      AuthFactory.login( user )
    }

  }

}