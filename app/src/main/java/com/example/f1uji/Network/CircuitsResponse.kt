package com.example.f1uji.Network

import com.example.f1uji.Common.Circuit

class CircuitsResponse (
    val MRData : DataCircuit
)
class DataCircuit(
    val CircuitTable : circuitTable
)
class circuitTable(
    val Circuits : List<Circuit>
)