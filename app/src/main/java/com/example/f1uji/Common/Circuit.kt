package com.example.f1uji.Common

class Circuit (
    val circuitId : String,
    val circuitName: String?,
    val Location : location?
)
class location(
    val lat : Float,
    val long : Float,
    val locality : String,
    val country : String
)