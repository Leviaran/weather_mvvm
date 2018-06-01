package ran.singletondev.gojek_android_asignment.dashboard

import android.Manifest
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.support.v4.content.ContextCompat
import io.reactivex.disposables.CompositeDisposable
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastInteractor
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastUseCase
import ran.singletondev.gojek_android_asignment.common.viewmodel.Response
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade
import javax.inject.Inject
import com.google.android.gms.location.LocationRequest
import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import ran.singletondev.gojek_android_asignment.BuildConfig
import ran.singletondev.gojek_android_asignment.services.GetAddressIntentService


/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * class for Viewmodel extends with Android Architecture Component MVVM architecture
 * @param forecaseInteractor
 * @param schedulerFacade
 */
const val LOCATION_PERMISSION_REQUEST_CODE = 2

class ForecastViewModel @Inject constructor (
        val forecaseInteractor: ForecastInteractor,
        val schedulersFacade: SchedulersFacade
    ) : ViewModel() {

    private val response = MutableLiveData<Response>()

    private val compositeDisposable = CompositeDisposable()


    /**
     * ViewHole onCleared with observable onClear
     */
    override fun onCleared() {
        compositeDisposable.clear()
    }

    /**
     * attach in activity to trigger loaddata api service
     */
    fun loadDataForecast(location : String) = loadForecast(forecaseInteractor, location)

    /**
     * MutableLiveData with generic Response
     */
    fun response () :MutableLiveData<Response> = response

    /**
     * with RxJava make flowable api service and turn into subscriber faucet by Response Status Data, Error, and Loading
     */
    fun loadForecast(forecastUseCase: ForecastUseCase, location : String){
        compositeDisposable.add(
                forecastUseCase.getForecast().getForecast(BuildConfig.ApixKey,location,5)
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe { response.value = Response.Loading }
                        .subscribe({forecast -> response.value =  Response.Success(forecast)}){
                            throwable -> response.value = Response.Error
                        }
        )
    }


    /**
     * trigger locate of startlocation service in Google Service
     */
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

    /**
     * get GeoCoder to find Address places
     */
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

