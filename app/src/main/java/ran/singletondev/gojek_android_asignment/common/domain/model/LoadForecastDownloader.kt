package ran.singletondev.gojek_android_asignment.common.domain.model

import io.reactivex.Single
import ran.singletondev.gojek_android_asignment.network.ApiService
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class LoadForecastDownloader @Inject constructor(private val apiService: ApiService) : LoadForecastRepository {
    override fun execute(): ApiService {
       return apiService
    }

}