package ir.moeindeveloper.countrypedia.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import ir.moeindeveloper.countrypedia.data.local.Constants
import ir.moeindeveloper.countrypedia.data.model.remote.Countries
import retrofit2.http.GET

interface CountryApiService {

    @GET(Constants.ApiEndPoints.all)
    suspend fun getCountries(): NetworkResponse<Countries,Countries>

}