package core.table

import operator.QueryOperator

class Table<T>(
    val metaData: TableMetaData
) {

    fun create() {

    }

    fun select(): QueryOperator<T> {
        return QueryOperator(metaData.columns, metaData.dataClass)
    }

    fun insert() {

    }

    fun update() {

    }

    fun delete() {

    }

}