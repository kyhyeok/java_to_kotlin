package scopefunction

fun main() {
    val person = Person("혁", 100)
    val letValue = person.let {
        it.age
    }

    val runValue = person.run {
        this.age
    }

    val alsoValue = person.also {
        it.age
    }

    val applyValue = person.apply {
        this.age
    }

    with(person) {
        println(name)
        println(thia.age)
    }
}

class Person(val name: String, val age: Int)

fun printPerson(person: Person?) {
    if (person != null) {
        println(person.name)
        println(person.age)
    }
}

fun printPersonLet(person: Person?) {
    person?.let {
        println(it.name)
        println(it.age)
    }
}

