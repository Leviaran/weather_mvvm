package ran.singletondev.gojek_android_asignment.dashboard

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastInteractor
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastUseCase
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.viewmodel.Response
import ran.singletondev.gojek_android_asignment.network.RetrofitHelper
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

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
                        .subscribe()
//                        .doOnSubscribe { response.value = Response.loading() }
//                        .subscribe({forecast -> response.value = Response.success(forecast)}){
//                            throwable -> response.value = Response.error(throwable)
//                        }
        )
    }


}