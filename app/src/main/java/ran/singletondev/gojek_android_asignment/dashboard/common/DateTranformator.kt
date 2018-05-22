package ran.singletondev.gojek_android_asignment.dashboard.common

import android.util.Log
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */


/**
 * create class DateTranformator to tranform Date String to String Date day by Locale Indonesia
 *
 */
class DateTranformator {
    companion object {
        fun convertToDay(date : String?) : String{

            var sdf = SimpleDateFormat("yyyy-MM-dd")
            var currentDate = sdf.parse(date)

            val dateFormat = SimpleDateFormat("EEEE", Locale("id","ID"))
            val dates = dateFormat.format(currentDate)
            Timber.e(dates.toString())

            return dates.toString()
        }
    }
}