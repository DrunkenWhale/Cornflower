package core.table

data class TableColumn(
    val name: String,
    val index: Int,
    val type: Int,
    val defaultValue: Any? = null,
    val isNullable: Boolean = false,
    val isPrimaryKey: Boolean = false,
    val isUnique: Boolean = false,
    val isIndex: Boolean = false,
)