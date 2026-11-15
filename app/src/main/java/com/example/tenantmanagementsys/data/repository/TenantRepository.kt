package com.example.tenantmanagementsys.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsys.data.local.TenantDao
import com.example.tenantmanagementsys.data.model.Tenant
// THE SINGLE DOOR into the data layer. The ViewModel only talks to this
// class, never to the DAO or the database directly
class TenantRepository(private val tenantDao: TenantDao) {
    // All tenants, kept up to date by Room
    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()
    // suspend: must be called from a coroutine
    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}