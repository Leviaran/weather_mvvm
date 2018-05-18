package ran.singletondev.gojek_android_asignment.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ran on 5/18/18.
 */

class SchedulersFacade {
    fun io() : Scheduler = Schedulers.io()

    fun computation() : Scheduler = Schedulers.computation()

    fun ui() : Scheduler = AndroidSchedulers.mainThread()
}