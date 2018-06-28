package ran.singletondev.weather_mvvm.di

import dagger.Module
import dagger.Provides
import ran.singletondev.weather_mvvm.common.domain.model.LoadForecastDownloader
import ran.singletondev.weather_mvvm.common.domain.model.LoadForecastRepository
import ran.singletondev.weather_mvvm.network.ApiService
import javax.inject.Singleton

/**
 * Created by ran on 5/18/18.
 */

@Module
class ForecastInteractorModule {

    @Singleton
    @Provides
    fun providesForecastInteractor(apiService: ApiService) : LoadForecastRepository = LoadForecastDownloader(apiService)
}