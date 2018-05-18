package ran.singletondev.gojek_android_asignment.di

import dagger.Module
import dagger.Provides
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastDownloader
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastRepository
import ran.singletondev.gojek_android_asignment.network.ApiService
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