package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class CurrencyItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)