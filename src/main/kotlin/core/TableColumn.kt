package core

import java.sql.Types

data class TableColumn<T>(
    val name: String,
    val index: Int,
    val type: Types,
    val defaultValue: List<T>,
    val nullable: Boolean,
    val isPrimaryKey: Boolean,
    val isUnique: Boolean,
    val isIndex: Boolean,
)