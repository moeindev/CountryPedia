package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import ir.moeindeveloper.countrypedia.data.model.local.OtherAcronym
import ir.moeindeveloper.countrypedia.data.model.local.OtherNames
import ir.moeindeveloper.countrypedia.data.model.local.RegionalBloc

@Keep
data class RegionalBlocItem(
    @SerializedName("acronym")
    val acronym: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("otherAcronyms")
    val otherAcronyms: List<String>,
    @SerializedName("otherNames")
    val otherNames: List<String>
) {

    fun getAsRegionalBloc(): RegionalBloc = RegionalBloc(acronym = acronym, name = name)

    fun getOtherAcronymsAsObject(): List<OtherAcronym> {
        val list = arrayListOf<OtherAcronym>()
        for (acronym in otherAcronyms) list.add(OtherAcronym(name = acronym))
        return list
    }


    fun getOtherNamesAsObject(): List<OtherNames> {
        val list = arrayListOf<OtherNames>()
        for (name in otherNames) list.add(OtherNames(name = name))
        return list
    }
}