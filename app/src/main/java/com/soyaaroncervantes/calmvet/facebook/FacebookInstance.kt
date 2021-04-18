package com.soyaaroncervantes.calmvet.facebook

import com.facebook.FacebookSdk

class FacebookInstance {
    companion object {
        init {
            FacebookSdk.fullyInitialize();
        }
    }
}