package com.keremkulac.karakoctekstilv2.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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



}