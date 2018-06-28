package ran.singletondev.weather_mvvm.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


class ForecastDetail (
        @Json(name = "forecastday") val forecastday : List<ForecastDay>
)
