package com.example.f1uji.DriverActivity

import com.example.f1uji.Common.Driver

interface DriverInterface {
    fun showDriverData(driver:Driver)
    fun showDriverDialog()
    fun showSearchError(error: Throwable)
}