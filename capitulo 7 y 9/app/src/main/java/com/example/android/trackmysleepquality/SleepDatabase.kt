package com.example.android.trackmysleepquality

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Asegúrate de que SleepNight y SleepDatabaseDao estén en el mismo paquete o importados correctamente
// import com.example.tuproyecto.SleepNight
// import com.example.tuproyecto.SleepDatabaseDao

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase<SleepDatabaseDao> : RoomDatabase() {

    // Declara un valor abstracto que devuelve SleepDatabaseDao
    abstract val sleepDatabaseDao: SleepDatabaseDao

    // Define un objeto complementario (Companion Object)
    companion object {
        // Declara una variable privada que acepte valores nulos INSTANCE para la base de datos.
        // Inicialízalo como nulo y anótalo con @Volatile para asegurar que su valor sea
        // siempre el más actualizado entre todos los hilos.
        @Volatile
        private var INSTANCE: SleepDatabase<Any?>? = null

        // Define el método getInstance() con un Context parámetro, que devolverá una referencia a SleepDatabase
        fun getInstance(context: Context): SleepDatabase<Any?> {
            // Envuelve la lógica de la base de datos en un bloque synchronized
            // para garantizar que solo un hilo a la vez acceda a este bloque.
            synchronized(this) {
                // Copia el valor actual de INSTANCE a una variable local, instance
                var instance = INSTANCE

                // Verifica si ya hay una base de datos almacenada en instance.
                if (instance == null) {
                    // Si instance es null, utiliza el generador de bases de datos para obtener una base de datos
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"


                        .build()

                    // Asigna la instancia recién creada a INSTANCE como paso final dentro de la declaración if
                    INSTANCE = instance
                }
                // Retorna la instancia de la base de datos (ya sea la existente o la recién creada)
                return instance
            }
        }
    }
}