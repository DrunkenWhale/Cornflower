package core.convert

import kotlin.reflect.KParameter

internal fun kParameterMappingToTableColumn(kParameter: KParameter) {
    kParameter.index
    kParameter.name
    kParameter.type
}