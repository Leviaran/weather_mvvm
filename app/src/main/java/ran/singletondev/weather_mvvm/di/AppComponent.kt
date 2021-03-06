package ran.singletondev.weather_mvvm.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ran.singletondev.weather_mvvm.App
import javax.inject.Singleton

/**
 * Created by ran on 5/18/18.
 */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class,
        ForecastInteractorModule::class,
        NetModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : App) : Builder
        fun buil() : AppComponent
    }

    fun inject(app : App)

}