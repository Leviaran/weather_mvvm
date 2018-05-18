package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json
import io.reactivex.Single

/**
 * Created by ran on 5/18/18.
 */


class Forecast (
        @Json(name = "location") var location : Location,
        @Json(name = "current") var current: Current,
        @Json(name = "forecastDetail") var forecastDetail: ForecastDetail
)