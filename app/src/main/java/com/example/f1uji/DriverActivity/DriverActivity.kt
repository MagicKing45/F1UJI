package com.example.f1uji.DriverActivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
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
        var idDriver = intent.getStringExtra(DRIVER_ID)!!
        driverViewModel.onDeepSearch(idDriver)
        driverViewModel.onSearchRequested(idDriver)
        binding.InfoButton.setOnClickListener {
            showDriverDialog()
        }
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
    override fun showDriverDialog() =
        DriverDialog().show(supportFragmentManager, "DriverDialog")
    class DriverDialog : DialogFragment() {
        private val driverViewModel: DriverViewModel by activityViewModels()


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(requireContext()).run {
                setTitle("World Championship Positions")
                //println(driverViewModel.deepDriver)
                var message: String = ""
                for(pair in driverViewModel.deepDriver){
                    message+=pair.first+":"+pair.second+"º/ "
                    println(pair.first+":"+pair.second+"º/ ")
                }
                setMessage(message)
                setPositiveButton("Ok", null)
                create()
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }
}