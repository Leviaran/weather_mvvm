package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 */

class ForecastDetail (
        @Json(name = "forecastday") var forecastday : List<ForecastDay>
)
