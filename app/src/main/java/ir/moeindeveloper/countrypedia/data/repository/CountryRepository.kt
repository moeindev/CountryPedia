package ir.moeindeveloper.countrypedia.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import io.objectbox.BoxStore
import ir.moeindeveloper.countrypedia.R
import ir.moeindeveloper.countrypedia.data.local.database.saveToDatabase
import ir.moeindeveloper.countrypedia.data.local.preference.AppConfig
import ir.moeindeveloper.countrypedia.data.model.local.Country
import ir.moeindeveloper.countrypedia.data.model.remote.CountryItem
import ir.moeindeveloper.countrypedia.data.remote.CountryApiHelper
import ir.moeindeveloper.countrypedia.core.state.CountryViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CountryRepository @Inject constructor(private val helper: CountryApiHelper,
                                            private val db: BoxStore,
                                            private val appConfig: AppConfig) {


    fun getCountries(shouldEmitLoading: Boolean = true): Flow<CountryViewState<List<Country>>> {
        return flow {
            if (shouldEmitLoading) emit(CountryViewState.loading(null))
            if (appConfig.isSaved) {
                //load from db:
                emit(CountryViewState.success(getCountryList()))
            } else {
                when(val response = helper.getCountries()) {
                    is NetworkResponse.Success -> {
                        //save info and then return data
                        saveToDatabase(response.body)
                        emit(CountryViewState.success(getCountryList()))
                    }

                    is NetworkResponse.NetworkError -> {
                        //throw error
                        emit(CountryViewState.error(msgId = R.string.error_network,data = null))
                    }

                    is NetworkResponse.UnknownError -> {
                        //throw error
                        emit(CountryViewState.error(msgId = R.string.error_unknown,data = null))
                    }

                    is NetworkResponse.ServerError -> {
                        //throw error
                        emit(CountryViewState.error(msgId = R.string.error_server,data = null))
                    }
                }
            }
        }
            .flowOn(Dispatchers.IO)
    }


    private fun saveToDatabase(countries: List<CountryItem>){
        for (country in countries) {
            db.saveToDatabase(country.convertToCountry())
            db.saveToDatabase(country.getOtherAcronyms())
            db.saveToDatabase(country.getOtherNames())
        }
        appConfig.isSaved = true
    }

    private fun getCountryList() : List<Country> {
        return db.boxFor(Country::class.java).all
    }

}