package ran.singletondev.gojek_android_asignment.network

import okhttp3.OkHttpClient
import org.reactivestreams.Subscriber
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.rx.SchedulersFacade
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ran on 5/18/18.
 */


const val DEFAULT_TIMEOUT : Long = 5

class RetrofitHelper {

    var retrofit : Retrofit
    var builder : OkHttpClient.Builder

    companion object {

        private object Singleton {
            val INSTANCES = RetrofitHelper()
        }

        fun getInstances() : RetrofitHelper = Singleton.INSTANCES

    }

    init {
        builder = OkHttpClient.Builder()
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

        retrofit = Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiBaseUrl)
                .build()

    }


}