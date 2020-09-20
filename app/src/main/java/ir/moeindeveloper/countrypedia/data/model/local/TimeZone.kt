package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class TimeZone(
    @Id var id: Long = 0,
    var time: String? = null
) {

    lateinit var country: ToOne<Country>
}