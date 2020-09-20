package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Currency(
    @Id var id: Long = 0,
    val code: String,
    val name: String?,
    val symbol: String?
)