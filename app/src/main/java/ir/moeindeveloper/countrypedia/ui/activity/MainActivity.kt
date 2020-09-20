package ir.moeindeveloper.countrypedia.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.viewModel.CountryViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val vm by viewModels<CountryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.countries.observe(this, {
            Log.e("state",it.status.toString())
            Log.e("countries",it.data.toString())
        })
    }

    override fun onResume() {
        super.onResume()
        vm.loadCountries()
    }
}