package com.example.f1uji.Selector

import com.example.f1uji.Common.Driver
import com.example.f1uji.Network.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SelectorRepository {
    suspend fun getDrivers(season: String) = try {
        withContext(Dispatchers.IO) {
            val driversResponse = API.getDrivers(season.lowercase())
            with(driversResponse) {
                Result.success(driversResponse.MRData.DriverTable.Drivers)
            }
        }
    } catch (e: Exception) {
        println(e)
        Result.failure(e)
    }
    suspend fun getCircuits(season: String) = try {
        withContext(Dispatchers.IO) {
            val circuitsResponse = API.getCircuits(season.lowercase())
            with(circuitsResponse) {
                Result.success(circuitsResponse.MRData.CircuitTable.Circuits)
            }
        }
    } catch (e: Exception) {
        println(e)
        Result.failure(e)
    }
    suspend fun getDriver(id: String) = try {
        withContext(Dispatchers.IO) {
            val driverResponse = API.getDriver(id)
            with(driverResponse) {
                Result.success(driverResponse.MRData.DriverTable.Drivers[0])
            }
        }
    } catch (e: Exception) {
        println(e)
        Result.failure(e)
    }
    suspend fun getCircuit(id: String) = try {
        withContext(Dispatchers.IO) {
            val circuitResponse = API.getCircuit(id)
            with(circuitResponse) {
                Result.success(circuitResponse.MRData.CircuitTable.Circuits[0])
            }
        }
    } catch (e: Exception) {
        println(e)
        Result.failure(e)
    }
    suspend fun getDeepDriver(id: String) = try {
        withContext(Dispatchers.IO) {
            val deepDriverResponse = API.getDeepDriver(id)
            with(deepDriverResponse) {
                var standings = deepDriverResponse.MRData.StandingsTable.StandingsLists
                val data = mutableListOf<Pair<String,String>>()
                for(year in standings)
                {
                    data.add(Pair(year.season,year.DriverStandings[0].position))
                }
                Result.success(data)
            }
        }
    } catch (e: Exception) {
        println(e)
        Result.failure(e)
    }
}