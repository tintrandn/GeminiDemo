package com.example.aitripdemo.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.aitripdemo.triplist.TripAdapter
import com.example.aitripdemo.triplist.TripViewModelFactory
import com.example.aitripdemo.triplist.commonitem.CommonItemAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesResources(): Resources {
        return application.resources
    }

    @Provides
    fun provideTripAdapter(
        foodAdapter: CommonItemAdapter,
        placesAdapter: CommonItemAdapter,
        accommodationAdapter: CommonItemAdapter
    ): TripAdapter {
        return TripAdapter(foodAdapter, placesAdapter, accommodationAdapter)
    }

    @Provides
    fun provideCommonItemAdapter(): CommonItemAdapter {
        return CommonItemAdapter()
    }

    @Provides
    fun provideTripViewModelFactory(): TripViewModelFactory {
        return TripViewModelFactory()
    }
}
