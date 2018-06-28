package ran.singletondev.weather_mvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ran.singletondev.weather_mvvm.dashboard.ForecastActivity
import ran.singletondev.weather_mvvm.dashboard.ForecastModule

/**
 * Created by ran on 5/18/18.
 */

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(ForecastModule::class))
    abstract fun bindForeCastActivity() : ForecastActivity
}