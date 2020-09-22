package ir.moeindeveloper.countrypedia.data.local.database

import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun <reified T: Any> BoxStore.saveToDatabase(data: List<T>) {
    boxFor<T>().put(data)
}

inline fun <reified T: Any> BoxStore.saveToDatabase(data: T) {
    boxFor<T>().put(data)
}