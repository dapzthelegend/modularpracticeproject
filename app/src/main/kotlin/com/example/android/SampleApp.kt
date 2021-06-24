package com.example.android

import com.example.android.di.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

/** Base class for maintaining global application state
 *
 * @See SplitCompatApplication
 */
class SampleApp : SplitCompatApplication() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initAppDependencyInjection()
        Timber.e("in App oncreare")
    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .build()
            .inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
