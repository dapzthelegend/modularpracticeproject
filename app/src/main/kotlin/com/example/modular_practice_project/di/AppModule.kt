package com.example.modular_practice_project.di

import android.app.Application
import com.example.modular_practice_project.SampleApp
import dagger.Module
import dagger.Provides


@Module
class AppModule {



    @Provides
    fun provideContext(application: SampleApp) = application.applicationContext
}