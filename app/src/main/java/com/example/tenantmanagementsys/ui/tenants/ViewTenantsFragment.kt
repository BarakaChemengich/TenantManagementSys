package com.example.tenantmanagementsys.ui.tenants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.tenantmanagementsys.databinding.FragmentViewTenantsBinding
import androidx.fragment.app.activityViewModels
import com.example.tenantmanagementsys.ui.viewmodel.TenantViewModel

class ViewTenantsFragment : Fragment() {
    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!
    // SAFE ARGS: reads the arguments that arrived with the action.
// ViewTenantsFragmentArgs was generated from the <argument> in nav_graph.xml
    private val args: ViewTenantsFragmentArgs by navArgs()
    private val viewModel: TenantViewModel by activityViewModels()
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
// The tenant sent from Add Tenant, or null if we came from the Dashboard
// (newTenant ← <argument android:name="newTenant"> in nav_graph.xml)
        val newTenant = args.newTenant
// DATA BINDING: give it to <variable name="tenant"> in the layout.
// newTenantTextView shows @{tenant.summary()}, so it updates itself
        binding.tenant = newTenant
// No new tenant? Hide "Just added", the box and the Call button together
// newTenantGroup ← the <Group> in fragment_view_tenants.xml
        if (newTenant == null) {
            binding.newTenantGroup.visibility = View.GONE
        }
        binding.callButton.setOnClickListener {
            if (newTenant != null) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${newTenant.phone}"))
                startActivity(intent)
            }
        }
// Show every saved tenant as text, one after another
        // Observe all tenants from the shared ViewModel
        viewModel.allTenantsText.observe(viewLifecycleOwner) { text ->
            binding.allTenantsTextView.text = text
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}