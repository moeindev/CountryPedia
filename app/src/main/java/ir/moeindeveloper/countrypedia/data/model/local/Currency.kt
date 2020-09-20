package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
data class Currency(
    @Id var id: Long = 0,
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null
)