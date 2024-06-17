package com.example.f1uji.CircuitActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.f1uji.Common.Circuit
import com.example.f1uji.R
import com.example.f1uji.databinding.CircuitActivityBinding

class CircuitActivity : AppCompatActivity(), CircuitInterface{
    private lateinit var binding : CircuitActivityBinding
    private val circuitViewModel : CircuitViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circuit_activity)
        binding = CircuitActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        circuitViewModel.onSearchRequested(intent.getStringExtra(CIRCUIT_ID)!!)
    }
    override fun onResume() {
        super.onResume()
       circuitViewModel.view = this
    }
    override fun onPause() {
        super.onPause()
        circuitViewModel.view = null
    }
    companion object {
        const val CIRCUIT_ID = "CIRCUIT_ID"
    }

    override fun showCircuitData(circuit: Circuit) {
        circuit.let {
            with(binding){

            }
        }
    }
}