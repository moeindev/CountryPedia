package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class RegionalBloc(
    @Id var id: Long = 0,
    var acronym: String? = null,
    var name: String? = null
) {
    @Backlink
    lateinit var otherAcronyms: ToMany<OtherAcronym>

    @Backlink
    lateinit var otherNames: ToMany<OtherNames>
}