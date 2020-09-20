package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Translation(
    @Id var id: Long = 0,
    var br: String,
    var de: String,
    var es: String,
    var fa: String,
    var fr: String,
    var hr: String,
    var it: String,
    var ja: String,
    var nl: String,
    var pt: String
) {
    lateinit var country: ToOne<Country>
}