package ran.singletondev.weather_mvvm.common.domain.interactor

import ran.singletondev.weather_mvvm.common.domain.model.LoadForecastRepository
import ran.singletondev.weather_mvvm.network.ApiService
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


/**
 * create class Interactor with inject on constructore with ForecastUseCaseRepository return value
 * @param loadForecastRepository
 * @constructor
 *
 */
class ForecastInteractor @Inject constructor(private val loadForecastRepository: LoadForecastRepository) : ForecastUseCase {

    override fun getForecast(): ApiService {
        return loadForecastRepository.execute()
    }

}
