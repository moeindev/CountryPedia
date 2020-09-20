package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Border(
    @Id var id: Long = 0,
    var countryName: String? = null
) {

    lateinit var country: ToOne<Country>
}