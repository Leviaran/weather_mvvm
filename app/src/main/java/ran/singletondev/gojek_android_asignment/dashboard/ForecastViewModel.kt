package ran.singletondev.gojek_android_asignment.dashboard

import android.Manifest
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastInteractor
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastUseCase
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.viewmodel.Response
import ran.singletondev.gojek_android_asignment.network.RetrofitHelper
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade
import javax.inject.Inject
import com.google.android.gms.location.LocationRequest
import android.Manifest.permission
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import ran.singletondev.gojek_android_asignment.BuildConfig
import ran.singletondev.gojek_android_asignment.splash.GetAddressIntentService
import ran.singletondev.gojek_android_asignment.splash.SplashActivity
import timber.log.Timber


/**
 * Created by ran on 5/18/18.
 */

const val LOCATION_PERMISSION_REQUEST_CODE = 2

class ForecastViewModel @Inject constructor (
        val forecaseInteractor: ForecastInteractor,
        val schedulersFacade: SchedulersFacade
    ) : ViewModel() {

    private val response = MutableLiveData<Response>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun loadDataForecast(location : String) = loadForecast(forecaseInteractor, location)

    fun response () :MutableLiveData<Response> = response

    fun loadForecast(forecastUseCase: ForecastUseCase, location : String){
        compositeDisposable.add(
                forecastUseCase.getForecast().getForecast(BuildConfig.ApixKey,location,5)
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe { response.value = Response.loading() }
                        .subscribe({forecast -> response.value = Response.success(forecast)}){
                            throwable -> response.value = Response.error(throwable)
                        }
        )
    }

    fun startLocationUpdates(context: Context,fusedLocationProviderClient: FusedLocationProviderClient, localCallback: LocationCallback) {
        if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            val locationRequest = LocationRequest()
            locationRequest.interval = 2000
            locationRequest.fastestInterval = 1000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY


            fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                    localCallback,
                    null)
        }
    }

    fun getAddress(context: Context, addressResultReceiver : ForecastActivity.LocationAddressResultReceiver, currentLocation : Location) {
        if (!Geocoder.isPresent()) {
            Toast.makeText(context,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(context, GetAddressIntentService::class.java)
        intent.putExtra("add_receiver", addressResultReceiver)
        intent.putExtra("add_location", currentLocation)
        context.startService(intent)
    }


}

