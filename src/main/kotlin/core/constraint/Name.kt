package core.constraint

/**
 * @param name
 *      if Name annotation be used in primary constructor param
 *      this column name will be replaced as `name` value.
 *      otherwise column name will be set as param name
 * */

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Name(val name: String)