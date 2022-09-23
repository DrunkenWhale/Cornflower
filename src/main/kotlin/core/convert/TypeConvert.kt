package core.convert

import core.anno.constraint.Index
import core.anno.constraint.Name
import core.anno.constraint.PrimaryKey
import core.anno.constraint.Unique
import core.table.TableColumn
import java.sql.Types.*
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.javaType

internal fun kParameterMappingToTableColumn(kParameter: KParameter): TableColumn {
    val name = if (kParameter.hasAnnotation<Name>()) {
        kParameter.findAnnotation<Name>()!!.name
    } else {
        kParameter.name
    }

    val index = kParameter.index
    val type = typeMappingToSqlType(kParameter.type)

    val isNullable: Boolean = kParameter.type.isMarkedNullable

    val isPrimaryKey: Boolean = kParameter.hasAnnotation<PrimaryKey>()
    val isUnique: Boolean = kParameter.hasAnnotation<Unique>()
    val isIndex: Boolean = kParameter.hasAnnotation<Index>()

    return TableColumn(
        name!!,
        index,
        type,
        null,
        isNullable,
        isPrimaryKey,
        isUnique,
        isIndex,
    )
}

/**
 *
 * @param kType  kotlin field type
 * @return java.sql.Types' enum value, if this type can't be match, will return null
 *
 * */
private fun typeMappingToSqlType(kType: KType): Int {
    return when (val typeName = kType.javaType.javaClass.simpleName) {
        "java.lang.Integer" -> INTEGER
        "java.lang.Long" -> BIGINT
        "java.lang.Double" -> DOUBLE
        "java.lang.Float" -> FLOAT
        "java.lang.Boolean" -> BOOLEAN
        "java.lang.String" -> VARCHAR
        else -> throw TypeNotPresentException(typeName, null)
    }
}