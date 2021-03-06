package eq.works

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices

class FetchLocation {

    @SuppressLint("MissingPermission")
    fun getLatLong(context: Context) {

        Log.i(context.applicationInfo.name, "Context passed to the lib")

        var lat: Float = 0.0f
        var lon: Float = 0.0f

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

        if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    context.checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                } else {
                    TODO("VERSION.SDK_INT < M")
                }) {
            fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        lat = location?.latitude?.toFloat() ?: 0.0F
                        lat = location?.longitude?.toFloat() ?: 0.0F
                    }
        } else if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    context.checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                } else {
                    TODO("VERSION.SDK_INT < M")
                }) {
            fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        // Got last known location. In some rare situations this can be null.
                        lat = location?.latitude?.toFloat() ?: 0.0F
                        lat = location?.longitude?.toFloat() ?: 0.0F
                    }
        }

        Log.d("latLong", "${lat.toString()} ${lon.toString()}")
    }
}
