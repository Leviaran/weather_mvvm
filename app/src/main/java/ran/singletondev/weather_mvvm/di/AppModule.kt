package ran.singletondev.weather_mvvm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ran.singletondev.weather_mvvm.App

/**
 * Created by ran on 5/18/18.
 */

@Module
class AppModule {

    @Provides
    fun provideContext(application : App) : Context = application.applicationContext

}