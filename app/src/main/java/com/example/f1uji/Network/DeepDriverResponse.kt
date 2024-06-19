package com.example.f1uji.Network

import com.example.f1uji.Common.AdvancedDriver

class DeepDriverResponse(
    val MRData : DeepDataDrivers
)
class DeepDataDrivers(
    val StandingsTable : standingsTable
)
class standingsTable(
    val StandingsLists : List<AdvancedDriver>
)