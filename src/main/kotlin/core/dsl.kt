package core

import core.table.Table
import core.table.TableMetaData
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

fun <T : Any> table(tableClass: KClass<T>): Table<T> {
    val parameters = tableClass.primaryConstructor!!.parameters
    val metaData = TableMetaData(tableClass.simpleName!!, listOf(), tableClass)
    return Table(metaData)
}