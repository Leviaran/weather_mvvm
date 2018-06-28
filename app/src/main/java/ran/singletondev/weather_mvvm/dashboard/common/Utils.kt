package ran.singletondev.weather_mvvm.dashboard.common

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import ran.singletondev.weather_mvvm.R

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * to create static access global typeface
 */
class Utils (context: Context) {
    val thinTypeface = ResourcesCompat.getFont(context, R.font.roboto_thin)
    val regularTypeface = ResourcesCompat.getFont(context, R.font.roboto_regular)
    val blackTypeface = ResourcesCompat.getFont(context, R.font.roboto_black)
}


