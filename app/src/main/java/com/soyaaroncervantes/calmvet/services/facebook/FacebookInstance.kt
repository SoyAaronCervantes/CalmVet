package com.soyaaroncervantes.calmvet.services.facebook

import com.facebook.FacebookSdk

class FacebookInstance {
    companion object {
        init {
            FacebookSdk.fullyInitialize();
        }
    }
}