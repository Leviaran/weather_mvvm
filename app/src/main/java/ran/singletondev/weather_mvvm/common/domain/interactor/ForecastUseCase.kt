package ran.singletondev.weather_mvvm.common.domain.interactor

import ran.singletondev.weather_mvvm.network.ApiService

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * interface class is a Data Module Usecase
 * @method getForecast
 */
interface ForecastUseCase {
    fun getForecast() : ApiService
}
