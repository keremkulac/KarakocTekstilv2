package com.keremkulac.karakoctekstilv2.ui.unitPrices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.keremkulac.karakoctekstilv2.databinding.FragmentUnitPricesBinding
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitPricesFragment : DialogFragment() {
    private val viewModel by viewModels<UnitPricesViewModel>()
    private lateinit var binding : FragmentUnitPricesBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentUnitPricesBinding.inflate(inflater)
         saveUnitPricesUpdates()
        getUnitPrices()
        return binding.root
    }

    private fun saveUnitPricesUpdates(){
        binding.saveUnitPricesUpdates.setOnClickListener{
            val unitPrices = UnitPrices(0,binding.yarnWeightPrices.text.toString(),binding.clothMeterPrices.text.toString())
            viewModel.saveUnitPricesUpdates(unitPrices)
        }
    }

    private fun getUnitPrices(){
        viewModel.unitPrices.observe(requireActivity()){unitPrices->
            unitPrices?.let {
                binding.yarnWeightPrices.setText( unitPrices.yarnWeightPrice)
                binding.clothMeterPrices.setText( unitPrices.clothMeterPrice)
            }


        }
    }
}