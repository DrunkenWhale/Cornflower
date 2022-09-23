package core.table

import kotlin.reflect.KClass

data class TableMetaData(
    val tableName: String,
    val columns: List<TableColumn>,
    val dataClass: KClass<*>
)


