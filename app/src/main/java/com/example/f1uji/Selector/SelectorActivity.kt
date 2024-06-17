package com.example.f1uji.Selector

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f1uji.CircuitActivity.CircuitActivity
import com.example.f1uji.Common.Circuit
import com.example.f1uji.Common.Driver
import com.example.f1uji.DriverActivity.DriverActivity
import com.example.f1uji.R
import com.example.f1uji.databinding.SelectorActivityBinding

class SelectorActivity : AppCompatActivity(), SelectorInteface {
    private lateinit var binding : SelectorActivityBinding
    private val selectorViewModel : SelectorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selector_activity)
        binding = SelectorActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            selectorViewModel.onSearchRequested(binding.spinnerSelector.selectedItem.toString(), binding.spinnerSelector2.selectedItem.toString())
        }
        if(selectorViewModel.state == 0) {
            if (selectorViewModel.drivers != null)
                showDrivers(selectorViewModel.drivers!!)
        }else {
            if (selectorViewModel.circuits != null)
                showCircuits(selectorViewModel.circuits!!)
        }
    }
    override fun onResume() {
        super.onResume()
        selectorViewModel.view = this
    }
    override fun onPause() {
        super.onPause()
        selectorViewModel.view = null
    }
    override fun showDrivers(drivers: List<Driver>) {
        drivers.let {
            with(binding) {
                RV.let {
                    it.adapter = DriversRVAdapter(drivers) {
                        selectorViewModel.onDriverSelected(it)
                    }
                    it.layoutManager = LinearLayoutManager(this@SelectorActivity)
                }
            }
        }
    }

    override fun showCircuits(circuits: List<Circuit>) {
        circuits.let {
            with(binding) {
                RV.let {
                    it.adapter = CircuitsRVAdapter(circuits) {
                        selectorViewModel.onCircuitSelected(it)
                    }
                    it.layoutManager = LinearLayoutManager(this@SelectorActivity)
                }
            }
        }
    }
    override fun goToDriverActivity(id: String) {
        val intent = Intent(this, DriverActivity::class.java)
        intent.putExtra(DriverActivity.DRIVER_ID, id)
        startActivity(intent)
    }
    override fun goToCircuitActivity(id: String) {
        val intent = Intent(this, CircuitActivity::class.java)
        intent.putExtra(CircuitActivity.CIRCUIT_ID, id)
        startActivity(intent)
    }
}