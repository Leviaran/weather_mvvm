package ran.singletondev.weather_mvvm.dashboard.common

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
//class DateTranformator {
//    companion object {
//        fun convertToDay(date : String?) : String{
//
//            var sdf = SimpleDateFormat("yyyy-MM-dd")
//            var currentDate = sdf.parse(date)
//
//            val dateFormat = SimpleDateFormat("EEEE", Locale("id","ID"))
//            val dates = dateFormat.format(currentDate)
//            Timber.e(dates.toString())
//
//            return dates.toString()
//        }
//    }
//}

fun String.DateTransformator() : String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.parse(this)

    val dateFormat = SimpleDateFormat("EEEE", Locale("id","ID"))
    val dates = dateFormat.format(currentDate)
    Timber.e(dates.toString())

    return dates.toString()
}