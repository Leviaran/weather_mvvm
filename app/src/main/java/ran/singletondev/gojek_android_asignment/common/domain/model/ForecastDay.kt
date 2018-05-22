package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */



class ForecastDay (
        @Json(name = "date") var date : String,
        @Json(name = "date_epoch") var date_epoch : String,
        @Json(name = "day") var day : Day
)