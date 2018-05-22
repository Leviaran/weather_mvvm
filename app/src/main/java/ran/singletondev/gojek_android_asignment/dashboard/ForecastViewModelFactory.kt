package ran.singletondev.gojek_android_asignment.dashboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import ran.singletondev.gojek_android_asignment.common.domain.interactor.ForecastInteractor
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * create factory builder in ViewModel
 */
class ForecastViewModelFactory (
        val forecastInteractor: ForecastInteractor,
        val schedulersFacade: SchedulersFacade ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(ForecastViewModel::class.java) -> {
                return ForecastViewModel(forecastInteractor, schedulersFacade) as (T)
            }
        }
        throw IllegalArgumentException(" Tidak diketahui kelasnya")
    }

}