package ran.singletondev.gojek_android_asignment.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ran.singletondev.gojek_android_asignment.dashboard.ForecastActivity
import ran.singletondev.gojek_android_asignment.dashboard.ForecastModule

/**
 * Created by ran on 5/18/18.
 */

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(ForecastModule::class))
    abstract fun bindForeCastActivity() : ForecastActivity
}