package com.example.f1uji.Network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Path

object API {
    private val apiInterface: APIInterface
    val driversMap = mutableMapOf<String, DriversResponse>()
    val circuitsMap = mutableMapOf<String, CircuitsResponse>()
    val driverMap = mutableMapOf<String, DriversResponse>()
    val circuitMap = mutableMapOf<String, CircuitsResponse>()
    val deepDriverMap = mutableMapOf<String, DeepDriverResponse>()

    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        apiInterface = Retrofit.Builder()
            .baseUrl("https://ergast.com/api/f1/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(APIInterface::class.java)
    }

    suspend fun getDrivers(@Path("id") season: String): DriversResponse {
        if (driversMap[season] != null)
            return driversMap[season]!!
        else {
            val drivers = apiInterface.getDrivers(season)
            driversMap[season] = drivers
            return drivers
        }
    }
    suspend fun getCircuits(@Path("id") season: String): CircuitsResponse {
        if (circuitsMap[season] != null)
            return circuitsMap[season]!!
        else {
            val circuits = apiInterface.getCircuits(season)
            circuitsMap[season] = circuits
            return circuits
        }
    }
    suspend fun getDriver(@Path("id") id: String): DriversResponse {
        if (driverMap[id] != null)
            return driverMap[id]!!
        else {
            val driver = apiInterface.getDriver(id)
            driverMap[id] = driver
            return driver
        }
    }
    suspend fun getCircuit(@Path("id") id: String): CircuitsResponse {
        if (circuitMap[id] != null)
            return circuitMap[id]!!
        else {
            val circuit = apiInterface.getCircuit(id)
            circuitMap[id] = circuit
            return circuit
        }
    }
    suspend fun getDeepDriver(@Path("id") id: String): DeepDriverResponse {
        if (deepDriverMap[id] != null)
            return deepDriverMap[id]!!
        else {
            val deepDriver = apiInterface.getDeepDriver(id)
            println(deepDriver.MRData.StandingsTable.StandingsLists[0].DriverStandings[0])
            deepDriverMap[id] = deepDriver
            println(deepDriver.toString())
            return deepDriver
        }
    }
}