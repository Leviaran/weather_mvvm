package ran.singletondev.weather_mvvm.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


class Current (
        @Json(name = "last_updated_epoch") val last_updated_epoch : String,
        @Json(name = "last_updated") val last_updated : String,
        @Json(name = "temp_c") val temp_c : String,
        @Json(name = "temp_f") val temp_f : String,
        @Json(name = "is_day") val is_day : String,
        @Json(name = "wind_mph") val wind_mph : String,
        @Json(name = "wind_kph") val wind_kph : String,
        @Json(name = "wind_degree") val wind_degree : String,
        @Json(name = "wind_dir") val wind_dir : String
)