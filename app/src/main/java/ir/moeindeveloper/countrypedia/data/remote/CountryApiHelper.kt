package ir.moeindeveloper.countrypedia.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import ir.moeindeveloper.countrypedia.data.model.remote.Countries

interface CountryApiHelper {

    suspend fun getCountries(): NetworkResponse<Countries, Nothing>

}