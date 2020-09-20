package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegionalBlocItem(
    @SerializedName("acronym")
    val acronym: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("otherAcronyms")
    val otherAcronyms: List<Any>,
    @SerializedName("otherNames")
    val otherNames: List<String>
)