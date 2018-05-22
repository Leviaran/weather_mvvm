package ran.singletondev.gojek_android_asignment.common.domain.interactor

import io.reactivex.Single
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastRepository
import ran.singletondev.gojek_android_asignment.network.ApiService
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
