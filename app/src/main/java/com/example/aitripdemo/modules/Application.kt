package com.example.aitripdemo.modules

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

open class Application : DaggerApplication() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .appModule(AppModule(this))
        .build()


    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any?>? {
        return dispatchingAndroidInjector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication?>? {
        return appComponent
    }

    open fun getAppComponent(): AppComponent {
        return appComponent
    }
}
