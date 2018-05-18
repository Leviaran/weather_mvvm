package ran.singletondev.gojek_android_asignment.common.domain.model

import io.reactivex.Single

/**
 * Created by ran on 5/18/18.
 */


class Forecast (
        var location : Location,
        var current: Current,
        var forecastDetail: ForecastDetail
) {

}