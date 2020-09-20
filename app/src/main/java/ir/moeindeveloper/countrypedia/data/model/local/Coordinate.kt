package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Coordinate(
    @Id var id: Long,
    var lat: Double,
    var lng: Double
) {
    lateinit var country: ToOne<Country>
}