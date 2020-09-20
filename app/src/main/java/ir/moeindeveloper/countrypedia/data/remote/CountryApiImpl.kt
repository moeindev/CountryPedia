package ir.moeindeveloper.countrypedia.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import ir.moeindeveloper.countrypedia.data.model.remote.Countries
import javax.inject.Inject

class CountryApiImpl @Inject constructor(private val service: CountryApiService): CountryApiHelper {

    override suspend fun getCountries(): NetworkResponse<Countries, Countries> = service.getCountries()

}