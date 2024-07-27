package com.kekulta.events.domain.models.values

sealed class Identifier(open val address: EmailAddress?, open val number: PhoneNumber?) {
    data class Phone(override val number: PhoneNumber) : Identifier(null, number)
    data class Email(override val address: EmailAddress) : Identifier(address, null)
    data class Both(override val address: EmailAddress, override val number: PhoneNumber) :
        Identifier(address, number)
}

fun Identifier.includes(other: Identifier): Boolean {
    val sameAddress = this.address != null && this.address == other.address
    val sameNumber = other.number != null && this.number.sameNumberAs(other.number)

    return sameNumber || sameAddress
}
