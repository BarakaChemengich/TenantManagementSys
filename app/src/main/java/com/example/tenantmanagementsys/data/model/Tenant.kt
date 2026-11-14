package com.example.tenantmanagementsys.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// @Entity: every Tenant is stored as one ROW in the "tenants" table
@Entity(tableName = "tenants")
data class Tenant(
    // Each property becomes a COLUMN
        val name: String,
        val phone: String,
        val rent: String,
    // A unique number for each row; Room fills it in (1, 2, 3...).
    // "= 0" means "not saved yet" and keeps Tenant(name, phone, rent) working
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) : Serializable {
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent: KSh $rent"
    }
}