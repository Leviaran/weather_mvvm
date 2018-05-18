package ran.singletondev.gojek_android_asignment.common.domain.interactor

import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class LoadForecastRepository @Inject constructor(val Forecast : Forecast) : LoadForecastUseCase {

    override fun execute(): Forecast {
        return Forecast
    }

}