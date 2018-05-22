package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


class Current (
        @Json(name = "last_updated_epoch") var last_updated_epoch : String,
        @Json(name = "last_updated") var last_updated : String,
        @Json(name = "temp_c") var temp_c : String,
        @Json(name = "temp_f") var temp_f : String,
        @Json(name = "is_day") var is_day : String,
        @Json(name = "wind_mph") var wind_mph : String,
        @Json(name = "wind_kph") var wind_kph : String,
        @Json(name = "wind_degree") var wind_degree : String,
        @Json(name = "wind_dir") var wind_dir : String
)