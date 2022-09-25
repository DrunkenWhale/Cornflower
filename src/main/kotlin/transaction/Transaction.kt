package transaction

import engine.Database


/**
 * Use Transaction
 *
 * Example:
 *
 * ```kotlin
 *
 * Transaction{
 *      CRUD
 * }
 *
 * ```
 *
 * */
class Transaction(op: () -> Unit) {
    init {
        Database.startTransaction()
        op()
        Database.endTransaction()
    }
}
