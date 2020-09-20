package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Language(
    @Id var id: Long = 0,
    var iso6391: String,
    var iso6392: String,
    var name: String,
    var nativeName: String
)