package com.example.f1uji.Network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIInterface {
    @Headers("Accept: application/json")
    @GET("{season}/drivers.json")
    suspend fun getDrivers(@Path("season") name: String): DriversResponse

    @GET("{season}/circuits.json")
    suspend fun getCircuits(@Path("season") name: String): CircuitsResponse

    @GET("drivers/{id}.json")
    suspend fun getDriver(@Path("id") name: String): DriversResponse

    @GET("circuits/{id}.json")
    suspend fun getCircuit(@Path("id") name: String): CircuitsResponse

    @GET("drivers/{id}/driverStandings.json")
    suspend fun getDeepDriver(@Path("id") name: String): DeepDriverResponse
}