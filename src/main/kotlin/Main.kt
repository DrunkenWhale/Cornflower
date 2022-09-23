import kotlin.reflect.full.primaryConstructor

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class C(val name: String)
annotation class D
data class Demo(
    @param:C("114514") @param:D val name: String? = "114514",
    @param:D val age: Int? = 114514

) {
}

fun main(args: Array<String>) {


    val clazz = Demo::class
    val member = clazz.primaryConstructor!!.parameters[0].type

    println(
        when ("114514") {
            in "191980", "114514" -> 114514
            else -> 1919810
        }
    )
}
