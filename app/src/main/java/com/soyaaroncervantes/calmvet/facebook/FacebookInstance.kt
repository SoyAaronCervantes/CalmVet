package com.soyaaroncervantes.calmvet.facebook

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class FacebookInstance {
    companion object {
        init {
            FacebookSdk.fullyInitialize();
            AppEventsLogger.activateApp( Application() )
        }
    }
}