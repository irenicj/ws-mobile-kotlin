package EQMobileWork

import kotlinx.serialization.Serializable

/**
 * Data class for Log call to submit the class object values to REST and
 * @author irenicj.ml
 * @param lat : Float - Latitude
 * @param lon  : Float - Longitude
 * @param time : Long - Timestamp for when Location Lat-lon was recorded
 *              -  If not passed explicitly this will be automatically
 *               recorded with system's current time
 *
 * @param ext : String - Extra field for logging Error on server side
 */
@Serializable
data class LocationEvent(val lat: Float? = 0.0f, val lon: Float? = 0.0f, val time: Long? = System.currentTimeMillis(), val ext: String?="No args passed in Log() call in library.")