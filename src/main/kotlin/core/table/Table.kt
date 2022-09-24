package core.table

import operator.CreateOperator
import operator.QueryOperator

class Table<T : Any>(
    val metaData: TableMetaData<T>
) {

    // need test
    fun create(): CreateOperator {
        return CreateOperator(metaData.tableName, metaData.columns)
    }

    fun select(): QueryOperator<T> {
        return QueryOperator(metaData.tableName, metaData.columns, metaData.dataClass)
    }

    fun insert() {

    }

    fun update() {

    }

    fun delete() {

    }

}