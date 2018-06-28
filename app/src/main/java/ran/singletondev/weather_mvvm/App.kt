package ran.singletondev.weather_mvvm

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ran.singletondev.weather_mvvm.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        DaggerAppComponent
                .builder()
                .application(this)
                .buil()
                .inject(this)


    }
}