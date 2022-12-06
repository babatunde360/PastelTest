package com.babatunde.pasteltest.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

object Utils {
    fun openTab(context: Context, url:String) {

        val packageName = "com.android.chrome"
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.setInstantAppsEnabled(true)
        val customBuilder = builder.build()
        customBuilder.intent.setPackage(packageName)
        customBuilder.launchUrl(context, Uri.parse(url))
    }
}