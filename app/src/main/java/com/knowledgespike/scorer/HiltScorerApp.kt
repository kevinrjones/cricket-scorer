package com.knowledgespike.scorer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class HiltScorerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // For release builds, you might plant a tree that sends logs
            // to a crash reporting service or a custom logging backend.
            // For now, we'll keep it simple and not log anything in release
            // or you could plant a tree that only logs errors.
            // Timber.plant(ReleaseTree()) // Example of a custom tree
        }
    }
}