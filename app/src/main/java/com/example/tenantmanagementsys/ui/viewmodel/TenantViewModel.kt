package com.example.tenantmanagementsys.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.tenantmanagementsys.data.local.TenantDatabase
import com.example.tenantmanagementsys.data.model.Tenant
import com.example.tenantmanagementsys.data.repository.TenantRepository
import kotlinx.coroutines.launch
// AndroidViewModel: a ViewModel that receives the Application (safe to keep)
class TenantViewModel(application: Application) : AndroidViewModel(application) {
    // Build the data layer once: database → DAO → repository
    private val repository =
        TenantRepository(TenantDatabase.getDatabase(application).tenantDao())
    // ── 1. THE DATA: Room's LiveData, updated whenever the table changes ──
    val tenants: LiveData<List<Tenant>> = repository.tenants
    // ── 2. TRANSFORMATIONS (unchanged from Stage 2) ──
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
    // ── 3. ACTIONS ──
    fun addTenant(name: String, phone: String, rent: String): Tenant? {
        val cleanName = name.trim()
        val cleanPhone = phone.trim()
        val cleanRent = rent.trim()
        if (cleanName.isEmpty() || cleanPhone.isEmpty() || cleanRent.isEmpty()) {
            return null
        }
        val tenant = Tenant(cleanName, cleanPhone, cleanRent)
// COROUTINE: save in the background so the screen never freezes.
// When the row is saved, Room updates "tenants" by itself
        viewModelScope.launch {
            repository.addTenant(tenant)
        }
        return tenant
    }
    // ── 4. LIFECYCLE LOGS ──
    init {
        Log.d("TENANT_LIFECYCLE", " TenantViewModel: created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d("TENANT_LIFECYCLE", " TenantViewModel: onCleared")
    }
}