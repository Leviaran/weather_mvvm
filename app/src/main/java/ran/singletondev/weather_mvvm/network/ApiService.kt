package ran.singletondev.weather_mvvm.network

import io.reactivex.Single
import ran.singletondev.weather_mvvm.common.domain.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ran on 5/18/18.
 */

const val apiBaseUrl : String = "https://api.apixu.com/"

interface ApiService {
    @GET("v1/forecast.json")
    fun getForecast(
            @Query("key") key : String,
            @Query("q") q : String,
            @Query("days") days : Int
    ) : Single<Forecast>
}