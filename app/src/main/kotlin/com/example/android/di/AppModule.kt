package com.example.android.di

import com.example.android.SampleApp
import dagger.Module
import dagger.Provides

/** App Module for the Application Component
 */
@Module
class AppModule {

    /** This provides the Context in the dependency graph

     */
    @Provides
    fun provideContext(application: SampleApp) = application.applicationContext
}
