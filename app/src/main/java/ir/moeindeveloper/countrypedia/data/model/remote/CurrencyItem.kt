package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import ir.moeindeveloper.countrypedia.data.model.local.Currency

@Keep
data class CurrencyItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
) {

    fun getAsCurrency(): Currency = Currency(code = code,name = name,symbol = symbol)
}