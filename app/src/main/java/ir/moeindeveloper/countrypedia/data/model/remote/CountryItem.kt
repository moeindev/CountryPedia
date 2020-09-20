package ir.moeindeveloper.countrypedia.data.model.remote


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import ir.moeindeveloper.countrypedia.data.model.local.*

@Keep
data class CountryItem(
    @SerializedName("alpha2Code")
    val alpha2Code: String? = null,
    @SerializedName("alpha3Code")
    val alpha3Code: String? = null,
    @SerializedName("altSpellings")
    val altSpellings: List<String>? = null,
    @SerializedName("area")
    val area: Double? = null,
    @SerializedName("borders")
    val borders: List<String>? = null,
    @SerializedName("callingCodes")
    val callingCodes: List<String>? = null,
    @SerializedName("capital")
    val capital: String? = null,
    @SerializedName("cioc")
    val cioc: String? = null,
    @SerializedName("currencies")
    val currencies: List<CurrencyItem>,
    @SerializedName("demonym")
    val demonym: String? = null,
    @SerializedName("flag")
    val flag: String? = null,
    @SerializedName("gini")
    val gini: Double? = null,
    @SerializedName("languages")
    val languages: List<LanguageItem>? = null,
    @SerializedName("latlng")
    val latLng: List<Double>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("nativeName")
    val nativeName: String? = null,
    @SerializedName("numericCode")
    val numericCode: String?? = null,
    @SerializedName("population")
    val population: Int? = null,
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("regionalBlocs")
    val regionalBlocs: List<RegionalBlocItem>,
    @SerializedName("subregion")
    val subregion: String? = null,
    @SerializedName("timezones")
    val timezones: List<String>? = null,
    @SerializedName("topLevelDomain")
    val topLevelDomain: List<String>? = null,
    @SerializedName("translations")
    val translations: TranslationsItem? = null
) {
    fun convertToCountry(): Country {
        val country = Country(alpha2Code = alpha2Code,
            alpha3Code = alpha3Code,
            area = area,
            capital = capital,
            cioc = cioc,
            demonym = demonym,
            flag = flag,
            gini = gini,
            name = name,
            nativeName = nativeName,
            numericCode = numericCode,
            population = population,
            region = region,
            subRegion = subregion)

        country.spelling.addAll(getAltSpelling())
        country.borders.addAll(getBorder())
        country.callingCodes.addAll(getCallingCode())
        country.currencies.addAll(getCurrency())
        country.languages.addAll(getLanguage())
        getCoordinates()?.let { coordinate ->
            country.coordinate.add(coordinate)
        }
        country.regionalBlocs.addAll(getRegionalBloc())
        country.timeZones.addAll(getTimeZone())
        country.toLevelDomain.addAll(getDomains())
        translations?.let {translationsItem ->
            country.translations.add(translationsItem.getAsTranslation())
        }
        return country
    }

    private fun getAltSpelling(): List<Spelling> {
        val list = arrayListOf<Spelling>()
        if (altSpellings != null) {
            for (spell in altSpellings) list.add(Spelling(spell = spell))
        }
        return list
    }

    private fun getBorder(): List<Border> {
        val list = arrayListOf<Border>()
        if (borders != null) {
            for (border in borders) list.add(Border(countryName = border))
        }
        return list
    }

    private fun getCallingCode(): List<CallingCode> {
        val list = arrayListOf<CallingCode>()
        if (callingCodes != null) {
            for (code in callingCodes) list.add(CallingCode(code = code))
        }
        return list
    }

    private fun getCurrency(): List<Currency> {
        val list = arrayListOf<Currency>()
        for (currency in currencies) list.add(currency.getAsCurrency())
        return list
    }

    private fun getLanguage(): List<Language> {
        val list = arrayListOf<Language>()
        if (languages != null) {
            for (lang in languages) list.add(lang.getAsLanguage())
        }
        return list
    }

    private fun getCoordinates(): Coordinate? {
        return if (latLng?.size == 2) {
            Coordinate(lat = latLng[0],lng = latLng[1])
        } else null
    }

    private fun getRegionalBloc(): List<RegionalBloc> {
        val list = arrayListOf<RegionalBloc>()
        for (region in regionalBlocs) list.add(region.getAsRegionalBloc())
        return list
    }

    fun getOtherAcronyms(): List<OtherAcronym> {
        val list = arrayListOf<OtherAcronym>()

        regionalBlocs.forEachIndexed { index, regionalBlocItem ->
            val otherAcronyms = regionalBlocItem.getOtherAcronymsAsObject()
            for (otherAcronym in otherAcronyms) {
                otherAcronym.regionalBloc.target = getRegionalBloc()[index]
                list.add(otherAcronym)
            }
        }

        return list
    }

    fun getOtherNames(): List<OtherNames> {
        val list = arrayListOf<OtherNames>()

        regionalBlocs.forEachIndexed { index, regionalBlocItem ->
            val otherNames = regionalBlocItem.getOtherNamesAsObject()
            for (otherName in otherNames) {
                otherName.regionalBloc.target = getRegionalBloc()[index]
                list.add(otherName)
            }
        }

        return list
    }

    private fun getTimeZone(): List<TimeZone> {
        val list = arrayListOf<TimeZone>()
        if (timezones != null) {
            for (time in timezones) list.add(TimeZone(time = time))
        }
        return list
    }

    private fun getDomains(): List<Domain> {
        val list = arrayListOf<Domain>()
        if (topLevelDomain != null) {
            for (domain in topLevelDomain) list.add(Domain(name = domain))
        }
        return list
    }
}