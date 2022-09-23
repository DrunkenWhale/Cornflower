package core.table

import core.table.TableColumn
import kotlin.reflect.KClass

data class TableMetaData(
    val tableName: String,
    val types: List<TableColumn>,
    val dataClass: KClass<*>
)


