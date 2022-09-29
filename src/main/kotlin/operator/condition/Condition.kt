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

    // equal to
    infix fun String.eq(condition: Any): String {
        if (condition is String) {
            return "$this = '$condition'"
        }
        return "$this = $condition"
    }

    // not equal to
    infix fun String.ne(condition: Any): String {
        if (condition is String) {
            return "$this != '$condition'"
        }
        return "$this != $condition"
    }

    // greater than or equal to
    infix fun String.ge(condition: Any): String {
        if (condition is String) {
            return "$this >= '$condition'"
        }
        return "$this >= $condition"
    }

    // greater than
    infix fun String.gt(condition: Any): String {
        if (condition is String) {
            return "$this > '$condition'"
        }
        return "$this > $condition"
    }

    // less than or equal to
    infix fun String.le(condition: Any): String {
        if (condition is String) {
            return "$this <= '$condition'"
        }
        return "$this <= $condition"
    }

    // less than
    infix fun String.lt(condition: Any): String {
        if (condition is String) {
            return "$this < '$condition'"
        }
        return "$this < $condition"
    }

    // like
    infix fun String.like(condition: Any): String {
        if (condition is String) {
            return "$this like '$condition'"
        }
        return "$this like $condition"
    }


    infix fun String.or(condition: String): String {
        return "$this OR $condition"
    }

    infix fun String.and(condition: String): String {
        return "$this AND $condition"
    }

}