package EQMobileWorkSample


public data class LocationEvent(val lat: Float, val lon: Float, val time: Long, val ext: String)

public class Library {
    fun setup(): Boolean {
        return true
    }

    fun log(event: LocationEvent) {
        // POST to API Server
    }
}

