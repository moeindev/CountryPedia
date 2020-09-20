package ir.moeindeveloper.countrypedia.data.model.local

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

@Entity
class OtherAcronym(
    @Id var id: Long,
    var name: String
) {

    lateinit var regionalBloc: ToOne<RegionalBloc>
}