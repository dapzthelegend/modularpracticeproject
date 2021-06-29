package com.example.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent]
 */
@Module
class ContextModule(private val application: Application) {

    /**
     * Provider method for binding [Context]
     *
     * @return Instance of Context
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideContext() = application.applicationContext
}
