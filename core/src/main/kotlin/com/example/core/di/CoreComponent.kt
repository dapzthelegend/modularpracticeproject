package com.example.core.di

import android.content.Context
import com.example.core.di.modules.ContextModule
import com.example.core.di.modules.DatabaseModule
import com.example.core.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Core Component that all module's component depends on
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface CoreComponent {

    /**
     * Provide Dependency graph context
     *
     * @return [Context]
     */
    fun context(): Context
}
