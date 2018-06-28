package ran.singletondev.weather_mvvm.dashboard

import dagger.Module
import dagger.Provides
import ran.singletondev.weather_mvvm.common.domain.interactor.ForecastInteractor
import ran.singletondev.weather_mvvm.rx.SchedulersFacade

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * ForecastModule provide every constructor by dagger
 */
@Module
class ForecastModule {

    @Provides
    fun providesForecastViewModelFactory(forecastInteractor: ForecastInteractor,
                                         schedulersFacade: SchedulersFacade) : ForecastViewModelFactory {
        return ForecastViewModelFactory(forecastInteractor, schedulersFacade)
    }
}