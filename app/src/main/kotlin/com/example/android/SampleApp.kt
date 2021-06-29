package com.example.android

import com.example.android.di.DaggerAppComponent
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.core.di.modules.ContextModule
import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

/** Base class for maintaining global application state
 *
 * @See SplitCompatApplication
 */
class SampleApp : SplitCompatApplication() {
    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
