import kotlin.reflect.full.primaryConstructor

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class C(val name: String)
annotation class D
data class Demo(
    val name: String? = "114514",
    val age: Int? = 1,
    val a: Int? = 2,
    val c: Int?,
    val b: Int? = 14,
)

fun main(args: Array<String>) {
    val clazz = Demo::class
    val member = clazz.primaryConstructor!!.parameters[0].type
}
