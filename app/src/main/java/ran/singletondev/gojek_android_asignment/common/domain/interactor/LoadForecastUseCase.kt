package ran.singletondev.gojek_android_asignment.common.domain.interactor

import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast

/**
 * Created by ran on 5/18/18.
 */

interface LoadForecastUseCase {
    fun execute() : Forecast
}