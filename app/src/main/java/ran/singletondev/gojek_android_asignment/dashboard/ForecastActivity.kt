package ran.singletondev.gojek_android_asignment.dashboard

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
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
import android.widget.*
import butterknife.OnClick
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


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

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    private lateinit var addressResultReceiver: LocationAddressResultReceiver

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var currentLocation: Location

    private lateinit var locationCallback: LocationCallback

    private var flag : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)



        var thinTypeface = ResourcesCompat.getFont(this, R.font.roboto_thin)
        var regularTypeface = ResourcesCompat.getFont(this, R.font.roboto_regular)
        var blackTypeface = ResourcesCompat.getFont(this, R.font.roboto_black)



        addressResultReceiver = LocationAddressResultReceiver(Handler())

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                currentLocation = locationResult!!.locations[0]
                forecastViewModel.getAddress(this@ForecastActivity, addressResultReceiver,currentLocation)
            }
        }

        forecastViewModel = ViewModelProviders.of(this, forecastViewModelFactory).get(ForecastViewModel::class.java)

        forecastViewModel.response().observe(this, Observer { response -> processResponse(response) })

        forecastViewModel.startLocationUpdates(this,fusedLocationClient, locationCallback)



        textError.typeface = thinTypeface

        textBigTemp.typeface = blackTypeface

        textLocation.typeface = thinTypeface



//        forecastViewModel.loadDataForecast()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Timber.e("is triggered2")
                    forecastViewModel.startLocationUpdates(this@ForecastActivity,fusedLocationClient,locationCallback)
                } else {
                    Toast.makeText(this, "Location permission not granted, " + "restart the app if you want the feature",
                            Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
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

        mainMenuView()

        insertIntoRecycler(forecast)
        insertIntoMainUI(forecast)

        Observable.just("Fake animation")
                .observeOn(AndroidSchedulers.mainThread())
                .delay(200, TimeUnit.MILLISECONDS)
                .doOnNext { triggerBottomSheet() }
                .subscribe()

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

    @OnClick(R.id.refresh_btn)
    fun refreshBtnClicked(){
//        forecastViewModel.loadDataForecast()

        val intent = intent
        finish()
        startActivity(intent)
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

    fun triggerBottomSheet(){
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)

        when {
            bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                Timber.e("clicked")
            }
            else -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                Timber.e("clicked else")
            }
        }
    }

    inner class LocationAddressResultReceiver internal constructor(handler: Handler) : ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {

            if (resultCode == 0) {
                forecastViewModel.getAddress(this@ForecastActivity,addressResultReceiver,currentLocation)
            }

            if (resultCode == 1) {
                Timber.e("address not found")
            }

            val currentAdd = resultData.getString("address_result")
            forecastViewModel.loadDataForecast(currentAdd)
        }
    }

    override fun onResume() {
        super.onResume()
//
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


}

