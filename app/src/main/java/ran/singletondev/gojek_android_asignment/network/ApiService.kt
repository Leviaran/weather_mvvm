package ran.singletondev.gojek_android_asignment.network

import io.reactivex.Observable
import io.reactivex.Single
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import retrofit2.http.GET

/**
 * Created by ran on 5/18/18.
 */

const val apiBaseUrl : String = "https://api.apixu.com/"

interface ApiService {
    @GET("v1/forecast.json?key=f4c0e1eecb164304b16200950181705&q=Paris&days=7")
    fun getForecast() : Single<Forecast>
}