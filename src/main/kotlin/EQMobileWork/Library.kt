package EQMobileWork


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
     * @param
     */
    fun log(event: LocationEvent) {

        // runs the func in background thread for posting the locationEvent without affecting UI thread
        doAsync {
            // do your background thread task
            retrofitService.postLocation(event).enqueue(
                    object : Callback<LocationEvent> {
                        override fun onResponse(call: Call<LocationEvent>, response: Response<LocationEvent>) {
                        }

                        override fun onFailure(call: Call<LocationEvent>, t: Throwable) {

                        }

                    }
            )
            uiThread {

            }
        }



    }

    fun isSuccessful(): Boolean {
        return status
    }



    companion object {
        var apiURL: String = "https://httpbin.org/post/"
        private var status: Boolean = false
        private lateinit var retrofit: Retrofit
        private lateinit var retrofitService: LocationPostService

    }

}

