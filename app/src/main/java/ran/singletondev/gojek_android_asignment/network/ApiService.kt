package ran.singletondev.gojek_android_asignment.network

import io.reactivex.Observable
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import retrofit2.http.GET

/**
 * Created by ran on 5/18/18.
 */

const val apiBaseUrl : String = "https://api.apixu.com/v1/forecast.json"

interface ApiService {
    @GET("key=f4c0e1eecb164304b16200950181705&q=Paris&days=7")
    fun getForecast() : Observable<Forecast>
}