package com.example.f1uji.CircuitActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1uji.Common.Circuit
import com.example.f1uji.Selector.SelectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CircuitViewModel :ViewModel(){
    var view: CircuitInterface? = null
        set(value) {
            field = value
            updateView()
        }
    var circuit: Circuit? = null
        set(value) {
            field = value
            if (value != null)
                displayCircuit(value)
        }

    private fun updateView() = view?.apply {

    }

    fun onSearchRequested(circuitID: String) {
        viewModelScope.launch(Dispatchers.Main) {
            SelectorRepository.getCircuit(circuitID)
                .onSuccess { circuit = it }
            .onFailure { view?.showSearchError(it)}
        }
    }
    private fun displayCircuit(circuit: Circuit) = view ?. apply {
        showCircuitData(circuit)
    }
}