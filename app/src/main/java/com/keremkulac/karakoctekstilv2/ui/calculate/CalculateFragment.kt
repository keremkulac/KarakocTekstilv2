package com.keremkulac.karakoctekstilv2.ui.calculate

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.keremkulac.karakoctekstilv2.R
import com.keremkulac.karakoctekstilv2.databinding.FragmentCalculateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculateFragment : Fragment() {
    private val viewModel by viewModels<CalculateViewModel>()
    private lateinit var binding : FragmentCalculateBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCalculateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchMenu()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeExchangeRates()

    }

    private fun observeExchangeRates() {
        viewModel.exchangeRateEuro.observe(requireActivity()) { it ->
            it.data?.result?.get("TRY")?.let { it1 ->
                Log.d("EURO", it1.toString()) }
        }
        viewModel.exchangeRateDollar.observe(requireActivity()) { it ->
            it.data?.result?.get("TRY")?.let { it1 -> Log.d("DOLAR", it1.toString()) }
        }
    }

    private fun setSearchMenu(){
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.unit_prices -> {
                        findNavController().navigate(R.id.action_calculateFragment_to_unitPricesFragment)
                        true
                    }
                    else -> false
                }
            }

        },viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}