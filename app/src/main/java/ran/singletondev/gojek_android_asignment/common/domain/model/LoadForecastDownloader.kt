package ran.singletondev.gojek_android_asignment.common.domain.model

import ran.singletondev.gojek_android_asignment.network.ApiService
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


/**
 * class LoadForecastDownloader with constructore injected by dagger
 * @param apiService
 * @return LoadForecastRepository
 */
class LoadForecastDownloader @Inject constructor(private val apiService: ApiService) : LoadForecastRepository {
    override fun execute(): ApiService {
       return apiService
    }

}