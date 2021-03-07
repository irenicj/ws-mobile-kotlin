package EQMobileWork


import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


/**
 * Libray init and log() func
 *
 * The fun log() will use the
 */
@ExperimentalSerializationApi
class Library {


    /**
     * Setup is the init call for the Library
     * this will set the url to POST the data to API
     *
     */
    fun setup(baseUrl: String): Boolean {

        apiURL = baseUrl
        retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(OkHttpClient().newBuilder().build())
            .build()

        retrofitService = retrofit.create(LocationPostService::class.java)

        return true
    }


    /**
     * Func to send @LocationEvent
     * sets the lat long from device automatically
     * @param Context
     */
    fun log(ctx: Context) {

        getLatLong(ctx)
        var locationEvent: LocationEvent = LocationEvent(lat, lon)


        // runs the func in background thread for posting the locationEvent without affecting UI thread
        doAsync {
            // do your background thread task
            retrofitService.postLocation(locationEvent).enqueue(
                object : Callback<LocationEvent> {
                    override fun onResponse(
                        call: Call<LocationEvent>,
                        response: Response<LocationEvent>
                    ) {
                    }

                    override fun onFailure(call: Call<LocationEvent>, t: Throwable) {

                    }

                }
            )
            uiThread {

            }
        }


    }


    // With Location Event if User wants to pass data explicitly
    fun log(locationEvent: LocationEvent, ctx: Context) {


        // runs the func in background thread for posting the locationEvent without affecting UI thread
        doAsync {
            // do your background thread task
            retrofitService.postLocation(locationEvent).enqueue(
                object : Callback<LocationEvent> {
                    override fun onResponse(
                        call: Call<LocationEvent>,
                        response: Response<LocationEvent>
                    ) {
                    }

                    override fun onFailure(call: Call<LocationEvent>, t: Throwable) {

                    }

                }
            )
            uiThread {

            }
        }


    }


    @SuppressLint("MissingPermission")
    private fun getLatLong(context: Context) {
        Log.i(context.applicationInfo.name, "Context passed to the lib")


        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        if (
            context.checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    lat = location?.latitude?.toFloat() ?: 0.0F
                    lat = location?.longitude?.toFloat() ?: 0.0F
                }
        } else if (
            context.checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    // Got last known location. In some rare situations this can be null.
                    lat = location?.latitude?.toFloat() ?: 0.0F
                    lat = location?.longitude?.toFloat() ?: 0.0F
                }
        }

        Log.d("latLong", "${lat.toString()} ${lon.toString()}")
    }

    fun isSuccessful(): Boolean {
        return status
    }


    companion object {
        var apiURL: String = "https://httpbin.org/post/"
        private var status: Boolean = false
        private lateinit var retrofit: Retrofit
        private lateinit var retrofitService: LocationPostService
        var lat: Float = 0.0f
        var lon: Float = 0.0f

    }

}

