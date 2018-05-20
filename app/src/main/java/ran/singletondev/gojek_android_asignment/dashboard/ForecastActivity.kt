package ran.singletondev.gojek_android_asignment.dashboard

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import ran.singletondev.gojek_android_asignment.dashboard.adapter.RecyclerAdapter
import ran.singletondev.gojek_android_asignment.dashboard.common.Utils
import timber.log.Timber
import javax.inject.Inject
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import android.widget.ProgressBar


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

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.bottom_sheet_layout)
    lateinit var bottom_sheet_layout : View

    @BindView(R.id.main_content_layout)
    lateinit var main_content_layout : View

    @BindView(R.id.progress)
    lateinit var progress : ProgressBar

    @BindView(R.id.linear_layout_menu)
    lateinit var linearLayoutMenu : LinearLayout

    private lateinit var forecastViewModel: ForecastViewModel

    private lateinit var recyclerAdapter : RecyclerAdapter


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
        loadingView()
    }

    private fun renderDataState(forecast : Forecast?) {
        Timber.e("Yesss")
        Timber.e(forecast?.forecast.toString())
        mainMenuView()

        insertIntoRecycler(forecast)
        insertIntoMainUI(forecast)

    }

    private fun renderErrorState(throwable: Throwable?) {
        Timber.e(throwable)
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        errorView()
    }

    fun insertIntoRecycler(forecast: Forecast?){
        recyclerAdapter = RecyclerAdapter(this, forecast?.forecast?.forecastday?.subList(1,5))
        val layoutManager = LinearLayoutManager(applicationContext)

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                layoutManager.getOrientation())

        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = recyclerAdapter
    }


    fun insertIntoMainUI(forecast: Forecast?){
        textBigTemp.text = "${forecast?.forecast?.forecastday?.get(0)?.day?.avgtemp_c}°"
        textLocation.text = forecast?.location?.name
    }

    fun loadingView(){
        progress.visibility = View.VISIBLE

        bottom_sheet_layout.visibility = View.GONE
        linearLayoutMenu.visibility = View.GONE
        main_content_layout.visibility = View.GONE

    }

    fun errorView(){
        linearLayoutMenu.visibility = View.VISIBLE

        progress.visibility = View.GONE
        bottom_sheet_layout.visibility = View.GONE
        main_content_layout.visibility = View.GONE
    }

    fun mainMenuView(){
        bottom_sheet_layout.visibility = View.VISIBLE
        main_content_layout.visibility = View.VISIBLE

        linearLayoutMenu.visibility = View.GONE
        progress.visibility = View.GONE

    }
}
