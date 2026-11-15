package com.example.tenantmanagementsys.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tenantmanagementsys.data.model.Tenant
// @Dao: the things we can do with the table. Room writes the code inside
@Dao
interface TenantDao {
    // suspend: can pause while the database works, without freezing the screen
    @Insert
    suspend fun insertTenant(tenant: Tenant)
    // LiveData: Room updates it automatically whenever the table changes
    @Query("SELECT * FROM tenants ORDER BY name")
    fun getAllTenants(): LiveData<List<Tenant>>
}