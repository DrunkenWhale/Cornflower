package operator.condition

/**
 * Suspension! use user's string instead!
 *
 * SQL sub sentence
 * condition
 * such as
 * LIKE, =, ASC
 */
object Condition {

    infix fun String.eq(condition: Any): String {
        if (condition is String) {
            return "$this='$condition'"
        }
        return "$this=$condition"
    }

    infix fun String.or(condition: String): String {
        return "$this OR $condition"
    }

    infix fun String.and(condition: String): String {
        return "$this AND $condition"
    }

}