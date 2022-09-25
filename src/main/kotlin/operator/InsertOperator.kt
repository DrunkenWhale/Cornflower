package operator

class InsertOperator<T> : Operator {

    private val list: MutableList<T> = mutableListOf()

    fun add(data: T): InsertOperator<T> {
        list.add(data)
        return this
    }

    fun add(dataList: List<T>): InsertOperator<T> {
        list.addAll(dataList)
        return this
    }

    override fun end(): Boolean {
        TODO("Not yet implemented")
    }
}