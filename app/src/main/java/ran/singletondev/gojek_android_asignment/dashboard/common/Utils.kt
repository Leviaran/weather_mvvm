package ran.singletondev.gojek_android_asignment.dashboard.common

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import ran.singletondev.gojek_android_asignment.R

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
    var thinTypeface = ResourcesCompat.getFont(context, R.font.roboto_thin)
    var regularTypeface = ResourcesCompat.getFont(context, R.font.roboto_regular)
    var blackTypeface = ResourcesCompat.getFont(context, R.font.roboto_black)
}


