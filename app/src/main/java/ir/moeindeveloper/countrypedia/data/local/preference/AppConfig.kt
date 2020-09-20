package ir.moeindeveloper.countrypedia.data.local.preference

import android.content.SharedPreferences
import ir.moeindeveloper.countrypedia.data.local.Constants
import javax.inject.Inject

class AppConfig @Inject constructor(private val sharedPreferences: SharedPreferences) {

    /**
     * this boolean is checking if the data from server saved to the database or not!
     */
    var isSaved: Boolean
    set(value) {
        sharedPreferences.edit().putBoolean(Constants.SharedPreferences.isSavedKey,value).apply()
    }
    get() {
        return sharedPreferences.getBoolean(Constants.SharedPreferences.isSavedKey,false)
    }


}