package ran.singletondev.gojek_android_asignment.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ran.singletondev.gojek_android_asignment.App
import javax.inject.Singleton

/**
 * Created by ran on 5/18/18.
 */

@Module
class AppModule {

    @Provides
    fun provideContext(application : App) : Context = application.applicationContext

}