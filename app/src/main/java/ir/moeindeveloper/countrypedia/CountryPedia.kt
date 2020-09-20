package ir.moeindeveloper.countrypedia

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CountryPedia: Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this,"en")
    }
}