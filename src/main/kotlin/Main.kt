import kotlin.reflect.cast
import kotlin.reflect.full.primaryConstructor


data class Demo(val name: String="114514") {
}

fun main(args: Array<String>) {


    val clazz = Demo::class
    val member = clazz.primaryConstructor!!.parameters[0].type
    println(1)
}
