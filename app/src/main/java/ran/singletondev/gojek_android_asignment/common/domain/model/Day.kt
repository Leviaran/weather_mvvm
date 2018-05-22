package ran.singletondev.gojek_android_asignment.common.domain.model

import com.squareup.moshi.Json

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


class Day (
        @Json(name = "maxtemp_c") var maxtemp_c : String,
        @Json(name = "mintemp_c") var mintemp_c : String,
        @Json(name = "avgtemp_c") var avgtemp_c : String
)