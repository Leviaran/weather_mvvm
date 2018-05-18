package ran.singletondev.gojek_android_asignment.common.domain.model

import io.reactivex.Single
import org.reactivestreams.Subscriber
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast

/**
 * Created by ran on 5/18/18.
 */

interface LoadForecastRepository {
    fun execute() : Single<Forecast>
}