package com.example.f1uji.Selector

import com.example.f1uji.Common.Circuit
import com.example.f1uji.Common.Driver

interface SelectorInteface {
    fun showDrivers(drivers : List<Driver>)
    fun showCircuits(circuits : List<Circuit>)
    fun goToDriverActivity(driverID : String)
    fun goToCircuitActivity(circuitID : String)
}