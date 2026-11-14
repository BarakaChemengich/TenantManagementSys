package com.example.tenantmanagementsys.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tenantmanagementsys.data.model.Tenant
// Which tables the database holds, and its version number
@Database(entities = [Tenant::class], version = 1)
abstract class TenantDatabase : RoomDatabase() {
    abstract fun tenantDao(): TenantDao
    companion object {
        // Only ONE database object for the whole app
        @Volatile
        private var INSTANCE: TenantDatabase? = null
        fun getDatabase(context: Context): TenantDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext, // the whole app, not one screen
                    TenantDatabase::class.java,
                    "tenant_database" // the file name on the phone
                ).build()
                INSTANCE = database
                database
            }
        }
    }
}