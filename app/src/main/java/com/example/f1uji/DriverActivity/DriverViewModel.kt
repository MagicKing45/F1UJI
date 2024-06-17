package com.example.f1uji.DriverActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.f1uji.Common.Driver
import com.example.f1uji.DriverActivity.DriverInterface
import com.example.f1uji.Selector.SelectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DriverViewModel : ViewModel() {
    var view: DriverInterface? = null
        set(value) {
            field = value
            updateView()
        }
    var driver: Driver? = null
        set(value) {
            field = value
            if (value != null)
                displayDriver(value)
        }

    private fun updateView() = view?.apply {

    }

    fun onSearchRequested(driverID: String) {
        viewModelScope.launch(Dispatchers.Main) {
            SelectorRepository.getDriver(driverID)
                .onSuccess { driver = it }
            //.onFailure { view?.showSearchError(it)
        }
    }
    private fun displayDriver(driver: Driver) = view ?. apply {
        showDriverData(driver)
    }
}