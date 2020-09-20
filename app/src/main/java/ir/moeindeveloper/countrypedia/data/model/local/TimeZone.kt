package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class TimeZone(
    @Id var id: Long,
    var time: String
) {

    lateinit var country: ToOne<Country>
}