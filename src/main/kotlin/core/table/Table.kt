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

    // join Operator
    infix fun innerJoin(table: Table<*>): JoinOperator {
        return join(table, "INNER JOIN")
    }

    infix fun leftJoin(table: Table<*>): JoinOperator {
        return join(table, "LEFT JOIN")

    }

    infix fun rightJoin(table: Table<*>): JoinOperator {
        return join(table, "RIGHT JOIN")

    }

    private fun join(table: Table<*>, joinType: String): JoinOperator {
        return JoinOperator(
            this.metaData.tableName,
            table.metaData.tableName,
            this.metaData.columns,
            table.metaData.columns,
            join = joinType
        )
    }

}