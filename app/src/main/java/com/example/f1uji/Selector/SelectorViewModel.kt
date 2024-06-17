package com.example.f1uji.Selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1uji.Common.Circuit
import com.example.f1uji.Common.Driver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectorViewModel : ViewModel() {
    var view: SelectorInteface? = null
        set (value){
            field = value
            updateView()
        }
    var drivers: List<Driver>? = null
        set(value) {
            field = value
            if (value != null)
                displayDrivers(value)
        }
    var circuits: List<Circuit>? = null
        set(value) {
            field = value
            if (value != null)
                displayCircuits(value)
        }
    var state = 0
    private fun updateView() = view?.apply {

    }

    fun onSearchRequested(Season: String, Class: String) {
        if (Class == "Drivers") {
            viewModelScope.launch(Dispatchers.Main) {
                SelectorRepository.getDrivers(Season)
                    .onSuccess { drivers = it }
                //.onFailure { view?.showSearchError(it)
            }
        } else {
            viewModelScope.launch(Dispatchers.Main) {
                SelectorRepository.getCircuits(Season)
                    .onSuccess { circuits = it }
                //.onFailure { view?.showSearchError(it)
            }
        }
    }
    private fun displayDrivers(drivers: List<Driver>) = view?.apply {
        state=0
        showDrivers(drivers)
    }
    private fun displayCircuits(circuits: List<Circuit>) = view?.apply {
        state=1
        showCircuits(circuits)
    }
    fun onDriverSelected(it: Driver) {
        view?.goToDriverActivity(it.driverId)
    }
    fun onCircuitSelected(it: Circuit) {
        view?.goToCircuitActivity(it.circuitId)
    }
}