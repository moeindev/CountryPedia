package ir.moeindeveloper.countrypedia.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.data.repository.CountryRepository
import ir.moeindeveloper.countrypedia.util.network.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountryViewModel @ViewModelInject constructor(private val repository: CountryRepository): ViewModel() {

    private val _countries = MutableLiveData<Resource<List<Country>>>()
    val countries: LiveData<Resource<List<Country>>> get() = _countries


    fun loadCountries(shouldEmitLoading: Boolean = true) {
        viewModelScope.launch {
            repository.getCountries(shouldEmitLoading)
                .collect {
                    _countries.value = it
                }
        }
    }

}