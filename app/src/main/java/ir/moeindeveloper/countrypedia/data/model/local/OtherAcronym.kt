package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class OtherAcronym(
    @Id var id: Long = 0,
    var name: String? = null
) {

    lateinit var regionalBloc: ToOne<RegionalBloc>
}