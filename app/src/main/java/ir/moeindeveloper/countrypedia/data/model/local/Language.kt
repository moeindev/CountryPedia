package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Language(
    @Id var id: Long = 0,
    var iso6391: String? = null,
    var iso6392: String? = null,
    var name: String? = null,
    var nativeName: String? = null
)