package com.bangkit.aktivio.core.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.Locale

object GeoHelper {
    fun getAddressFromLocation(latitude: Double, longitude: Double, context: Context): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        var addressText: String? = null
        try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                addressText = address.getAddressLine(0)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return addressText
    }

    fun getCurrentLatLong(context: Context): LatLng {
        val geocoder = Geocoder(context, Locale.getDefault())
        var latitude = 0.0
        var longitude = 0.0
        try {
            val addresses: List<Address>? = geocoder.getFromLocationName("Jakarta", 1)
            if (!addresses.isNullOrEmpty()) {
                val address: Address = addresses[0]
                latitude = address.latitude
                longitude = address.longitude
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return LatLng(latitude, longitude)
    }
}