package com.example.aitripdemo.widget

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WidgetModule {
    @ContributesAndroidInjector
    abstract fun contributePassengersCabinClassWidget(): FlightCabinClassWidget
}
