package ran.singletondev.gojek_android_asignment.common.viewmodel

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast

/**
 * Created by ran on 5/18/18.
 * Email in randy.arba@gmail.com
 * Github in https://github.com/Leviaran
 * Publication in https://medium.com/@randy.arba
 */

/**
 * class Response with create status in subcriber
 * @constructor
 * @param status
 * @param data
 * @param error
 */
//class Response constructor(val status : Status,
//                                   @Nullable var data : Forecast?,
//                                   @Nullable var error : Throwable?){
//
//    companion object {
//
//        fun loading() : Response = Response(Status.LOADING, null, null)
//
//        fun success(@NonNull data : Forecast?) : Response = Response(Status.SUCCESS, data, null)
//
//        fun error(@NonNull error: Throwable?) : Response = Response(Status.ERROR, null, error)
//    }
//
//}

sealed class Response {
    object Loading : Response()
    data class Success (val data : Forecast?) : Response()
    object Error : Response()
}

