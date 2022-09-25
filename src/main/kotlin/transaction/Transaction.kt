package transaction

import engine.Database

class Transaction(op: () -> Unit) {
    init {
        Database.startTransaction()
        op()
        Database.endTransaction()
    }
}
