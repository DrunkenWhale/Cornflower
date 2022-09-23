package core

import core.convert.kParameterMappingToTableColumn
import core.table.Table
import core.table.TableMetaData
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

fun <T : Any> table(tableClass: KClass<T>): Table<T> {

    val columnList = tableClass.primaryConstructor!!.parameters.map {
        kParameterMappingToTableColumn(it)
    }

    val metaData = TableMetaData(tableClass.simpleName!!, columnList, tableClass)
    return Table(metaData)
}