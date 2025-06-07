package com.example.android.diceroller

import android.app.Application
import timber.log.Timber // Esto se importará automáticamente después de añadir la dependencia

class PusherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 4. Inicializar Timber aquí
        Timber.plant(Timber.DebugTree())
    }
}