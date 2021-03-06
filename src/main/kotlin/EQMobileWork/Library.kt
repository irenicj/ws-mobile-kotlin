package EQMobileWork


import EQMobileWork.Android.FetchLocationLatLong
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import javax.naming.Context

/**
 * Libray init and log() func
 *
 * The fun log() will use the
 */
@ExperimentalSerializationApi
class Library {

    /**
     * Setup call for the Library init
     * This will set the url to POST the data to API
     */
    fun setup(baseUrl: String): Boolean {
        retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .client(OkHttpClient().newBuilder().build())
                .build()
        retrofitService = retrofit.create(LocationPostService::class.java)
        return true
    }


    /**
     * Func to send @LocationEvent
     * @param
     */
    fun log(event: LocationEvent, ctx: Objects) {

        // runs the func in background thread for posting the locationEvent without affecting UI thread
        // for safety if not invoked from out side the retrofit instance
//        doAsync {

//            val x: Call<LocationEvent> = retrofitService.postLocation(event)
//            x.execute().isSuccessful // throws illegalstateexception now as using test api url





        retrofitService.postLocation(event).enqueue(
                object : Callback<LocationEvent> {
                    override fun onResponse(call: Call<LocationEvent>, response: Response<LocationEvent>) {
                    }

                    override fun onFailure(call: Call<LocationEvent>, t: Throwable) {
                    }

                }
        )
//        }


    }


    companion object {
        var successful: Boolean = false
        private lateinit var retrofit: Retrofit
        private lateinit var retrofitService: LocationPostService
    }

}

