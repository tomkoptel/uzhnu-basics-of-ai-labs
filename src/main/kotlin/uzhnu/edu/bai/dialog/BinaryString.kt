package uzhnu.edu.bai.dialog

/**
 * Strings represents raw messages
 */
data class BinaryString(private val value: String) {
    operator fun get(index: Int): Char = value[index]
    val length get() = value.length
}
