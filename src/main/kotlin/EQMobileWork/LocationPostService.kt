package EQMobileWork

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * Retrofit interface service class
 */
interface LocationPostService {

    @Headers("Content-Type: application/json")
    @POST("/")
    fun postLocation(@Body locationEvent: LocationEvent) : Call<LocationEvent>

}