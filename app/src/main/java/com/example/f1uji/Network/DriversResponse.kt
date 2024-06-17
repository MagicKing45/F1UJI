package com.example.f1uji.Network

import com.example.f1uji.Common.Circuit
import com.example.f1uji.Common.Driver
import com.squareup.moshi.Json

class DriversResponse (
    val MRData : DataDrivers
)
class DataDrivers(
    val DriverTable : driverTable
)
class driverTable(
    val Drivers : List<Driver>
)