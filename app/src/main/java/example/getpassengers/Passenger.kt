package example.getpassengers

import java.io.Serializable

data class Passenger(
    val fName: String,
    val lName: String,
    val phone: String
) : Serializable {
    override fun toString(): String {
        return "${fName.trim()} ${lName.trim()} : ${phone.trim()}"
    }
}
