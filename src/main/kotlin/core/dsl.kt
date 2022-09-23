package core

import kotlin.reflect.KClass

fun <T : Any> table(tableClass: KClass<T>): Table<T> {
    val metaData = TableMetaData(tableClass.simpleName!!, listOf(), tableClass)
    return Table(metaData)
}