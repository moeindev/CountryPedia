package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import ir.moeindeveloper.countrypedia.data.model.local.Translation

@Keep
data class TranslationsItem(
    @SerializedName("br")
    val br: String,
    @SerializedName("de")
    val de: String,
    @SerializedName("es")
    val es: String,
    @SerializedName("fa")
    val fa: String,
    @SerializedName("fr")
    val fr: String,
    @SerializedName("hr")
    val hr: String,
    @SerializedName("it")
    val it: String,
    @SerializedName("ja")
    val ja: String,
    @SerializedName("nl")
    val nl: String,
    @SerializedName("pt")
    val pt: String
) {

    fun getAsTranslation(): Translation =
        Translation(br = br,de = de,
            es = es,fa = fa,
            fr = fr,hr = hr,
            it = it,ja = ja,
            nl = nl,pt = pt)
}