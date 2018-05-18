package ran.singletondev.gojek_android_asignment.common.domain.interactor

import io.reactivex.Single
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.domain.model.LoadForecastRepository
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class ForecastInteractor @Inject constructor(private val loadForecastRepository: LoadForecastRepository) : ForecastUseCase {

    override fun getForecast(): Single<Forecast> {
        return loadForecastRepository.execute()
    }

}
