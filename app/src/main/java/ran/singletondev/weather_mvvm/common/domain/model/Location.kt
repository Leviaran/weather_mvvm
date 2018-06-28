package ran.singletondev.weather_mvvm.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

class Location (
        @Json(name = "name") val name : String,
        @Json(name = "region") val region : String,
        @Json(name = "country") val country : String,
        @Json(name = "lat") val lat : String,
        @Json(name = "lon") val lon : String,
        @Json(name = "tz_id") val tz_id : String,
        @Json(name = "localtime_epoch") val localtime_epoch : String,
        @Json(name = "localtime") val localtime : String
)