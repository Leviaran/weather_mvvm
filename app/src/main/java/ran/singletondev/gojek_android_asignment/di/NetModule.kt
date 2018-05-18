package ran.singletondev.gojek_android_asignment.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ran.singletondev.gojek_android_asignment.network.ApiService
import ran.singletondev.gojek_android_asignment.network.apiBaseUrl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by ran on 5/18/18.
 */

@Module
class NetModule {

    @Provides
    fun providesApiServices(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return  Retrofit.Builder()
                .baseUrl(apiBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun providesOkHttpClient() : OkHttpClient {
        var logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }
}