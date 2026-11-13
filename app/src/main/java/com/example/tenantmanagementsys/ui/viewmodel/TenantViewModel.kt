package com.example.tenantmanagementsys.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.tenantmanagementsys.data.model.Tenant
import com.example.tenantmanagementsys.data.repository.TenantRepository


class TenantViewModel : ViewModel() {

    // ── 1. THE DATA ──
    private val _tenants = MutableLiveData<List<Tenant>>(TenantRepository.tenants.toList())

    val tenants: LiveData<List<Tenant>> = _tenants

    // ── 2. TRANSFORMATIONS:  ──
    // map() makes a NEW LiveData that updates whenever tenants changes
    val tenantCountText: LiveData<String> = tenants.map { list ->
        "Tenants\n${list.size}"
    }

    val totalRentText: LiveData<String> = tenants.map { list ->
        val total = list.sumOf { it.rent.toDoubleOrNull() ?: 0.0 }
        "Total rent\nKSh %,.0f".format(total)
    }

    val allTenantsText: LiveData<String> = tenants.map { list ->
        if (list.isEmpty()) "No tenants yet. Add one from the Dashboard."
        else list.joinToString("\n\n") { it.summary() }
    }

    // Checks the input and saves the tenant.
    // Returns the new Tenant, or null if the input was not valid
    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()

        // all three fields are required
        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }

        val tenant = Tenant(cleanName, cleanPhone, cleanRent)

        TenantRepository.addTenant(tenant)                      // ask the DATA LAYER to store it
        _tenants.value = TenantRepository.tenants.toList()      // every observer updates

        return tenant
    }

    // ── 4. LIFECYCLE LOGS ──
    init {
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", "TenantViewModel: onCleared")
    }
}