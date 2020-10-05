package ir.moeindeveloper.countrypedia.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.moeindeveloper.countrypedia.core.intent.UserIntent
import ir.moeindeveloper.countrypedia.core.model.CountryModel
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.data.repository.CountryRepository
import ir.moeindeveloper.countrypedia.core.state.CountryViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CountryViewModel @ViewModelInject constructor(private val repository: CountryRepository): ViewModel(), CountryModel<CountryViewState<List<Country>>, UserIntent> {

    private val _countries = MutableLiveData<CountryViewState<List<Country>>>()

    override val intents: Channel<UserIntent> = Channel(Channel.UNLIMITED)

    override val state: LiveData<CountryViewState<List<Country>>>
        get() = _countries


    init {
        handleIntents()
    }


    private fun handleIntents() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {userIntent->
                when(userIntent) {
                    UserIntent.fetchCountries -> loadCountries()
                    UserIntent.realoadCountries -> loadCountries(false)
                }
            }
        }
    }


    fun loadCountries(shouldEmitLoading: Boolean = true) {
        viewModelScope.launch {
            repository.getCountries(shouldEmitLoading)
                .collect {
                    _countries.value = it
                }
        }
    }

}