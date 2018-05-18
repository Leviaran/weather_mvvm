package ran.singletondev.gojek_android_asignment.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.AndroidInjection
import ran.singletondev.gojek_android_asignment.R
import ran.singletondev.gojek_android_asignment.common.domain.model.Forecast
import ran.singletondev.gojek_android_asignment.common.viewmodel.Response
import ran.singletondev.gojek_android_asignment.common.viewmodel.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ran on 5/18/18.
 */

class ForecastActivity : AppCompatActivity() {

    @Inject
    lateinit var forecastViewModelFactory: ForecastViewModelFactory

    @BindView(R.id.error_text)
    lateinit var textError : TextView

    @BindView(R.id.big_temp)
    lateinit var textBigTemp : TextView

    @BindView(R.id.location)
    lateinit var textLocation : TextView

    private lateinit var forecastViewModel: ForecastViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        var thinTypeface = ResourcesCompat.getFont(this, R.font.roboto_thin)
        var regularTypeface = ResourcesCompat.getFont(this, R.font.roboto_regular)
        var blackTypeface = ResourcesCompat.getFont(this, R.font.roboto_black)

        textError.typeface = thinTypeface

        textBigTemp.typeface = blackTypeface

        textLocation.typeface = thinTypeface

        forecastViewModel = ViewModelProviders.of(this, forecastViewModelFactory).get(ForecastViewModel::class.java)

        forecastViewModel.response().observe(this, Observer { response -> processResponse(response) })

        forecastViewModel.loadDataForecast()

    }

    private fun processResponse(response: Response?) {
        when (response?.status) {
            Status.LOADING -> renderLoadingState()

            Status.SUCCESS -> renderDataState(response.data)

            Status.ERROR -> renderErrorState(response.error)
        }
    }

    private fun renderLoadingState() {
        Timber.e("Loading")
    }

    private fun renderDataState(forecast : Forecast?) {
        Timber.e(forecast.toString())
    }

    private fun renderErrorState(throwable: Throwable?) {
        Timber.e(throwable)
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}
