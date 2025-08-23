package functions

fun createPerson (firstName: String, lastName: String): Person {
    if (firstName.isEmpty()) {
        throw IllegalArgumentException("firstName은 비어있을 수 없습니다. $firstName")
    }

    if (lastName.isEmpty()) {
        throw IllegalArgumentException("lastName은 비어있을 수 없습니다. $firstName")
    }

    return Person(firstName, lastName, 1)
}


fun createPerson2 (firstName: String, lastName: String): Person {
    fun validateName(name: String, fieldName: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("${fieldName}은 비어있을 수 없습니다. $name")
        }
    }

    validateName(firstName, "firstName")
    validateName(lastName, "lastName")

    return Person(firstName, lastName, 1)
}
