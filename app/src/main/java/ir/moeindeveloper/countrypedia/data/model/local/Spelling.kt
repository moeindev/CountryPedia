package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Spelling(
    @Id var id: Long = 0,
    var spell: String
) {
    lateinit var country: ToOne<Country>
}