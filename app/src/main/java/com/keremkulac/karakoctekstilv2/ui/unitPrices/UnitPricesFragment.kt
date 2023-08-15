package com.keremkulac.karakoctekstilv2.ui.unitPrices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.keremkulac.karakoctekstilv2.databinding.FragmentUnitPricesBinding
import com.keremkulac.karakoctekstilv2.model.unitPrices.UnitPrices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitPricesFragment : DialogFragment() {
    private val viewModel by viewModels<UnitPricesViewModel>()
    private lateinit var binding : FragmentUnitPricesBinding
    private var savedUnitPrices : UnitPrices? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        binding = FragmentUnitPricesBinding.inflate(inflater)
         saveUnitPricesUpdates()
        getUnitPrices()
        observeExchangeRates()
        return binding.root
    }

    private fun saveUnitPricesUpdates(){
        binding.saveUnitPricesUpdates.setOnClickListener{
            val yarnWeightPrice  = binding.yarnWeightPrices.text.toString()
            val clothMeterPrice = binding.clothMeterPrices.text.toString()
            val unitPrices : UnitPrices
            if(yarnWeightPrice == "" || clothMeterPrice == ""){
                Toast.makeText(requireContext(),"Lütfen tüm alanları doldurunuz",Toast.LENGTH_SHORT).show()
            }else{
                unitPrices = UnitPrices(1,yarnWeightPrice,clothMeterPrice,false)

                if(yarnWeightPrice.equals("") && clothMeterPrice.equals("") && savedUnitPrices == null){
                    viewModel.saveUnitPricesUpdates(unitPrices)
                }else{
                    unitPrices.updated = true
                    viewModel.updateUnitPrices(unitPrices)
                }
            }
        }
    }


    private fun getUnitPrices(){
        viewModel.unitPrices.observe(requireActivity()){unitPrices->
            unitPrices?.let {
                savedUnitPrices = UnitPrices(1,unitPrices.yarnWeightPrice,unitPrices.clothMeterPrice,unitPrices.updated)
                binding.yarnWeightPrices.setText( unitPrices.yarnWeightPrice)
                binding.clothMeterPrices.setText( unitPrices.clothMeterPrice)
            }
        }
    }

    private fun observeExchangeRates() {
        viewModel.exchangeRateEuro.observe(requireActivity()) { it ->
            it.let {
                binding.exchangeRateEuro.setText(it.data?.result?.get("TRY").toString())
            }
        }
        viewModel.exchangeRateDollar.observe(requireActivity()) { it ->
            it.let {
                binding.exchangeRateDollar.setText(it.data?.result?.get("TRY").toString())
            }
        }
    }

}