package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

class Location (
        @Json(name = "name") var name : String,
        @Json(name = "region") var region : String,
        @Json(name = "country") var country : String,
        @Json(name = "lat") var lat : String,
        @Json(name = "lon") var lon : String,
        @Json(name = "tz_id") var tz_id : String,
        @Json(name = "localtime_epoch") var localtime_epoch : String,
        @Json(name = "localtime") var localtime : String
)