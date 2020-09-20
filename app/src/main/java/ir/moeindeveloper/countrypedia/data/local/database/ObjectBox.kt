package ir.moeindeveloper.countrypedia.data.local.database

import android.content.Context
import android.util.Log
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import ir.moeindeveloper.countrypedia.BuildConfig
import ir.moeindeveloper.countrypedia.data.model.local.MyObjectBox


object ObjectBox {

    private lateinit var boxStore: BoxStore

    fun init(context: Context): BoxStore {
        if (::boxStore.isInitialized && !boxStore.isClosed) {
            return boxStore
        }

        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        if (BuildConfig.DEBUG) {
            val started = AndroidObjectBrowser(boxStore).start(context)
            Log.i("ObjectBrowser", "Started: $started")
        }
        return boxStore
    }
}