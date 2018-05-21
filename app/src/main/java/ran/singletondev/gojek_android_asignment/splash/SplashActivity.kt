package ran.singletondev.gojek_android_asignment.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ran.singletondev.gojek_android_asignment.R
import timber.log.Timber
import android.widget.ProgressBar
import com.google.android.gms.tasks.OnSuccessListener


class SplashActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private var lastLocation : Location? = null
    private lateinit var resultReceiver : AddressResultReceiver

    private lateinit var addressOutput : String

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private fun startIntentService(){
        val intent = Intent(this, FetchAddressIntentService::class.java).apply {
            putExtra(Constants.RECEIVER,resultReceiver)
            putExtra(Constants.LOCATION_DATA_EXTRA, lastLocation)
        }
        startService(intent)
    }

    @SuppressLint("MissingPermission")
    fun fetchAddressButtonHandler(){

    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {

        var mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = mLocationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l = mLocationManager.getLastKnownLocation(provider)

            if (l == null) {
                continue
            }
            if (bestLocation == null || l!!.getAccuracy() < bestLocation.accuracy) {
                bestLocation = l
            }
        }
        return if (bestLocation == null) {
            null
        } else bestLocation

    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationProviderClient.lastLocation.addOnSuccessListener(this, object : OnSuccessListener<Location>{
            override fun onSuccess(location: Location?) {
                lastLocation = location

                if (lastLocation == null ){
                    return
                }

                if (!Geocoder.isPresent()){
                    return
                }
                startIntentService()


            }
        })

        fetchAddressButtonHandler()
    }

    internal inner class AddressResultReceiver(handler: Handler) : ResultReceiver(handler) {

        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

            // Display the address string
            // or an error message sent from the intent service.
            addressOutput = resultData?.getString(Constants.RESULT_DATA_KEY) ?: ""
            Timber.e(addressOutput)

            // Show a toast message if an address was found.
            if (resultCode == Constants.SUCCESS_RESULT) {
                Timber.e("alamat ditemukan")
            }

        }
    }

}
