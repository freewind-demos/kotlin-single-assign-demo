package example

import kotlin.reflect.KProperty


fun main(args: Array<String>) {
    val user = User()
    user.name = "Kotlin"
    println(user.name)
    
    user.name = "Kotlin2"
}

class User {
    var name by SingleAssign<String>()
}

class SingleAssign<T> {
    private var value: T? = null
    operator fun getValue(ref: Any?, property: KProperty<*>): T {
        if (!initialized()) throw IllegalStateException("the property ${property.name} has not been initialized")
        return value!!
    }

    operator fun setValue(ref: Any?, property: KProperty<*>, newValue: T) {
        if (initialized()) throw IllegalStateException("can't init the property ${property.name} twice")
        value = newValue
    }

    private fun initialized(): Boolean {
        return value != null
    }
}