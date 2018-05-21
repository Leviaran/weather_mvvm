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
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
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

    fun loadDataForecast() = loadForecast(forecaseInteractor)

    fun response () :MutableLiveData<Response> = response

    fun loadForecast(forecastUseCase: ForecastUseCase){
        compositeDisposable.add(
                forecastUseCase.getForecast()
                        .subscribeOn(schedulersFacade.io())
                        .observeOn(schedulersFacade.ui())
                        .doOnSubscribe { response.value = Response.loading() }
                        .subscribe({forecast -> response.value = Response.success(forecast)}){
                            throwable -> response.value = Response.error(throwable)
                        }
        )
    }


}