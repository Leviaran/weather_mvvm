package ran.singletondev.gojek_android_asignment.common.domain.interactor

import io.reactivex.Single
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.network.ApiService

/**
 * Created by ran on 5/18/18.
 */

interface ForecastUseCase {
    fun getForecast() : ApiService
}
