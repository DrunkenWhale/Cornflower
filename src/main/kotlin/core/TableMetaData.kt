package core

import kotlin.reflect.KClass

data class TableMetaData(
    val tableName: String,
    val types: List<TableColumn>,
    val dataClass: KClass<*>
)


