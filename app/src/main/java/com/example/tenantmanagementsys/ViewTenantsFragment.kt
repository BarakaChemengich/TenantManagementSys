package com.example.tenantmanagementsys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tenantmanagementsys.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {


    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// For now, hide the "Just added" section. Practical 3 shows it when a tenant is passed in.
        binding.newTenantGroup.visibility = View.GONE
        val tenants = TenantRepository.tenants
        if (tenants.isEmpty()) {
            binding.allTenantsTextView.text = "No tenants yet. Add one from the Dashboard."
        } else {
            binding.allTenantsTextView.text = tenants.joinToString("\n\n") { it.summary() }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}