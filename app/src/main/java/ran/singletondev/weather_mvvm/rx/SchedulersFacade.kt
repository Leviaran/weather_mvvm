package ran.singletondev.weather_mvvm.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class SchedulersFacade @Inject constructor() {
    fun io() : Scheduler = Schedulers.io()

    fun computation() : Scheduler = Schedulers.computation()

    fun ui() : Scheduler = AndroidSchedulers.mainThread()
}