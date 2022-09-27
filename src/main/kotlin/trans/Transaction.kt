package trans

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

typealias transaction = Transaction

class Transaction(op: () -> Unit) {
    init {
        Database.startTransaction()
        op()
        Database.endTransaction()
    }
}
