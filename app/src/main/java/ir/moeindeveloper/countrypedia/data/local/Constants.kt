package ir.moeindeveloper.countrypedia.data.local

import ir.moeindeveloper.countrypedia.data.model.local.Country

object Constants {
    const val baseURL: String = "https://restcountries.eu/rest/v2/"
    object ApiEndPoints{
        const val all: String = "all"
    }

    object SharedPreferences {
        const val isSavedKey: String = "is_saved"
        const val prefs_key: String = "countryPedia"
    }

    object SharedElements {
        const val image: String = "image"
        const val title: String = "title"
        fun getTransitionName(viewType: String, countryID: Long): String = "${viewType}_${countryID}"
    }
}