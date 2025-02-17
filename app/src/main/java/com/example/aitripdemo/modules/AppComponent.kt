package com.example.aitripdemo.modules

import com.example.aitripdemo.PlanTripActivity
import com.example.aitripdemo.widget.FlightCabinClassWidget
import com.example.aitripdemo.widget.WidgetModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        WidgetModule::class
    ]
)
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }

    override fun inject(application: Application)

    fun inject(activity: PlanTripActivity)

    fun inject(flightCabinClassWidget: FlightCabinClassWidget)

}
