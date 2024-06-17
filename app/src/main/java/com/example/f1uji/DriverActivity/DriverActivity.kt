package com.example.f1uji.DriverActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.f1uji.Common.Driver
import com.example.f1uji.R
import com.example.f1uji.databinding.DriverActivityBinding

class DriverActivity: AppCompatActivity(), DriverInterface {
    private lateinit var binding : DriverActivityBinding
    private val driverViewModel : DriverViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_activity)
        binding = DriverActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        driverViewModel.onSearchRequested(intent.getStringExtra(DRIVER_ID)!!)
    }
    override fun onResume() {
        super.onResume()
        driverViewModel.view = this
    }
    override fun onPause() {
        super.onPause()
        driverViewModel.view = null
    }
    companion object {
        const val DRIVER_ID = "DRIVER_ID"
    }

    override fun showDriverData(driver: Driver) {
        driver.let {
            with(binding){
                DriverName.text = driver.givenName+" "+ driver.familyName
                NationalityText.text="Nationality: "+driver.nationality
                BirthText.text = "Date of Birth: "+driver.dateOfBirth
            }
        }
    }
}