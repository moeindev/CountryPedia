package ir.moeindeveloper.countrypedia.di

import android.content.Context
import android.content.SharedPreferences
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.objectbox.BoxStore
import ir.moeindeveloper.countrypedia.data.local.Constants
import ir.moeindeveloper.countrypedia.data.local.database.ObjectBox
import ir.moeindeveloper.countrypedia.data.remote.CountryApiHelper
import ir.moeindeveloper.countrypedia.data.remote.CountryApiImpl
import ir.moeindeveloper.countrypedia.data.remote.CountryApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CountryModule {

    @Provides
    @Singleton
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SharedPreferences.prefs_key,Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): BoxStore = ObjectBox.init(context)

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .baseUrl(Constants.baseURL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): CountryApiService = retrofit.create(CountryApiService::class.java)

    @Provides
    @Singleton
    fun providesApiHelper(apiImpl: CountryApiImpl): CountryApiHelper = apiImpl


}