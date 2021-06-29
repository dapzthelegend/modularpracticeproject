package com.example.android.di

import com.example.android.SampleApp
import com.example.core.di.CoreComponent
import com.example.core.di.scopes.AppScope
import dagger.Component

/**Application Component with dependencies on the CoreComponent
 *
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    /**
     * Adds the application to the dependency graph and Injects
     * dependencies on the application
     *
     * @param application: The Application Class
     */
    fun inject(application: SampleApp)
}
