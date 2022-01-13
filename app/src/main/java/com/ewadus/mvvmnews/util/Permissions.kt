package com.ewadus.mvvmnews.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.ewadus.mvvmnews.util.Constants.Companion.PERMISSION_LOCATION_REQUEST_CODE
import com.vmadalin.easypermissions.EasyPermissions

object Permissions {

    fun hasPermissionLocation(context: Context): Boolean {
        return EasyPermissions.hasPermissions(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

    }

    fun requestLocationPermission(fragment: Fragment) {
        EasyPermissions.requestPermissions(
            fragment,
            "This app need location permission",
            PERMISSION_LOCATION_REQUEST_CODE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

    }


}