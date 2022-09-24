package core.convert

import core.constraint.Index
import core.constraint.Name
import core.constraint.PrimaryKey
import core.constraint.Unique
import core.table.TableColumn
import java.sql.Types.*
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation


/**
 * this function only will be used in generator `CREATE` SQL Sentence
 *
 * @param kParameter param in data class primary constructor
 * @return a full TableColumn class
 * @throws TypeNotPresentException if param not be defined in mapping rule
 *
 * */
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
    return when (val typeName = kType.toString()) {
        TypeName.KtIntName -> INTEGER
        TypeName.KtLongName -> BIGINT
        TypeName.ktDoubleName -> DOUBLE
        TypeName.KtFloatName -> FLOAT
        TypeName.KtBooleanName -> BOOLEAN
        TypeName.KtStringName -> VARCHAR
        else -> throw TypeNotPresentException(typeName, null)
    }
}

