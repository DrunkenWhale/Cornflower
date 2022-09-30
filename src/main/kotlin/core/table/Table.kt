package core.table

import operator.*

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

    fun insert(): InsertOperator<T> {
        return InsertOperator(metaData.tableName, metaData.columns)
    }

    fun update(): UpdateOperator {
        return UpdateOperator(metaData.tableName)
    }

    fun delete(): DeleteOperator {
        return DeleteOperator(metaData.tableName)
    }

}