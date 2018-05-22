package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json
import io.reactivex.Single

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */



class Forecast (
        @Json(name = "location") var location : Location,
        @Json(name = "current") var current: Current,
        @Json(name = "forecast") var forecast: ForecastDetail
)