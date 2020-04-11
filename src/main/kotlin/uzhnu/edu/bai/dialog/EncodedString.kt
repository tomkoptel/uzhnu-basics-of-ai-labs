package uzhnu.edu.bai.dialog

/**
 * Strings represents encoded messages
 */
data class EncodedString(private val value: String) {
    operator fun get(index: Int) = value[index]
    val length get() = value.length
}
