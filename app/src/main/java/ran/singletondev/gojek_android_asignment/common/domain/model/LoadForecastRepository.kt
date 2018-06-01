package ran.singletondev.gojek_android_asignment.common.domain.model

import ran.singletondev.gojek_android_asignment.network.ApiService

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * interface class is a Data Module for Repository
 * @method execute
 */
interface LoadForecastRepository {
    fun execute() : ApiService
}