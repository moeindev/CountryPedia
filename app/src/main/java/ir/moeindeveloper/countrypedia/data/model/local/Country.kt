package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne

@Entity
data class Country(
   @Id var id: Long = 0,
   //1
   var alpha2Code: String,
   //2
   var alpha3Code: String,
   //4
   var area: Double? = null,
   //7
   var capital: String,
   //8
   var cioc: String? = null,
   //10
   var demonym: String,
   //11
   var flag: String,
   //12
   var gini: Double? = null,
   //15
   var name: String,
   //16
   var nativeName: String,
   //17
   var numericCode: String? = null,
   //18
   var population: Int,
   //19
   var region: String,
   //21
   var subRegion: String
) {

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