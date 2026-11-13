package com.example.tenantmanagementsys.data.repository

import com.example.tenantmanagementsys.data.model.Tenant

object TenantRepository {
    val tenants = mutableListOf<Tenant>()
    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }
    fun totalRent(): Double {
        return tenants.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
    }
}