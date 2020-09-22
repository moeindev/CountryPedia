package ir.moeindeveloper.countrypedia.data.model.local

import android.os.Parcelable
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import java.io.Serializable

@Entity
data class Country(
   @Id var id: Long = 0,
   //1
   var alpha2Code: String? = null,
   //2
   var alpha3Code: String? = null,
   //4
   var area: Double? = null,
   //7
   var capital: String? = null,
   //8
   var cioc: String? = null,
   //10
   var demonym: String? = null,
   //11
   var flag: String? = null,
   //12
   var gini: Double? = null,
   //15
   var name: String? = null,
   //16
   var nativeName: String? = null,
   //17
   var numericCode: String? = null,
   //18
   var population: Int? = null,
   //19
   var region: String? = null,
   //21
   var subRegion: String? = null
): Serializable {

    //3
    @Backlink
    lateinit var spelling: ToMany<Spelling>

    //5
    @Backlink
    lateinit var borders: ToMany<Border>

    //6
    @Backlink
    lateinit var callingCodes: ToMany<CallingCode>

    //9
    lateinit var currencies: ToMany<Currency>

    //13
    lateinit var languages: ToMany<Language>

    //14
    @Backlink
    lateinit var coordinate: ToMany<Coordinate>

    //20
    lateinit var regionalBlocs: ToMany<RegionalBloc>

    //22
    @Backlink
    lateinit var timeZones: ToMany<TimeZone>

    //23
    @Backlink
    lateinit var toLevelDomain: ToMany<Domain>

    //24
    @Backlink
    lateinit var translations: ToMany<Translation>


}