package core.table

import operator.QueryOperator

class Table<T : Any>(
    val metaData: TableMetaData<T>
) {

    fun create() {

    }

    fun select(): QueryOperator<T> {
        return QueryOperator<T>(metaData.columns, metaData.dataClass)
    }

    fun insert() {

    }

    fun update() {

    }

    fun delete() {

    }

}