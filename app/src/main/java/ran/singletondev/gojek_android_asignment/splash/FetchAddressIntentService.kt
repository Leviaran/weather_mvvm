package ran.singletondev.gojek_android_asignment.splash

import android.app.IntentService
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.ResultReceiver
import timber.log.Timber
import java.io.IOException
import java.util.*

/**
 * Created by ran on 5/20/18.
 */

object Constants {
    const val SUCCESS_RESULT = 0
    const val FAILURE_RESULT = 1
    const val PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress"
    const val RECEIVER = "$PACKAGE_NAME.RECEIVER"
    const val RESULT_DATA_KEY = "${PACKAGE_NAME}.RESULT_DATA_KEY"
    const val LOCATION_DATA_EXTRA = "${PACKAGE_NAME}.LOCATION_DATA_EXTRA"
}

class FetchAddressIntentService : IntentService(Constants.RECEIVER) {

    private var receiver: ResultReceiver? = null

    override fun onHandleIntent(intent: Intent?) {
        val geocoder = Geocoder(this, Locale.getDefault())

        intent ?: return

        var errorMessage = ""

        val location = intent.getParcelableExtra<Location>(
                Constants.LOCATION_DATA_EXTRA)


        var address : List<Address> = emptyList()

        try {
            address = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
            )
        } catch (ioException : IOException){
            errorMessage = "Service no avalalible"
            Timber.e(ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = "kesalahan lat long"
            Timber.e(illegalArgumentException)
        }

        if (address.isEmpty()){
            if (errorMessage.isEmpty()){
                errorMessage = "Alamat tidak ditemukan"
                Timber.e(errorMessage)
            }
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage)
        } else {
            val addresses = address[0]

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.

            val addressFragments = with(address) {
                (0..addresses.maxAddressLineIndex).map { addresses.getAddressLine(it) }
            }
            Timber.e("Alamat tidak ditemukan")
            deliverResultToReceiver(Constants.SUCCESS_RESULT,
                    addressFragments.joinToString(separator = "\n"))
        }
    }

    private fun deliverResultToReceiver(resultCode: Int, message: String) {
        val bundle = Bundle().apply { putString(Constants.RESULT_DATA_KEY, message) }
        receiver?.send(resultCode, bundle)
    }

}