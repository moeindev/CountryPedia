package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Domain(
    @Id var id: Long = 0,
    var name: String? = null
) {

    lateinit var country: ToOne<Country>
}