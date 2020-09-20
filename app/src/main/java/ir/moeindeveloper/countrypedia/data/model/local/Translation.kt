package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Translation(
    @Id var id: Long = 0,
    var br: String? = null,
    var de: String? = null,
    var es: String? = null,
    var fa: String? = null,
    var fr: String? = null,
    var hr: String? = null,
    var it: String? = null,
    var ja: String? = null,
    var nl: String? = null,
    var pt: String? = null
) {
    lateinit var country: ToOne<Country>
}