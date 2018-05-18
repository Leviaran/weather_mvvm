package ran.singletondev.gojek_android_asignment.common.viewmodel

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast

/**
 * Created by ran on 5/18/18.
 */

class Response private constructor(val status : Status,
                                   @Nullable var data : Forecast?,
                                   @Nullable var error : Throwable?){

    companion object {

        fun loading() : Response = Response(Status.LOADING, null, null)

        fun success(@NonNull data : Forecast?) : Response = Response(Status.SUCCESS, data, null)

        fun error(@NonNull error: Throwable?) : Response = Response(Status.ERROR, null, error)
    }

}