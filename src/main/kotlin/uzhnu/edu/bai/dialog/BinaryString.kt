package uzhnu.edu.bai.dialog

/**
 * Strings represents raw messages
 */
data class BinaryString(private val value: String) {
    operator fun get(index: Int): Char = value[index]
    val length get() = value.length

    fun encode() : EncodedString {
        return getHammingCodewordIndices(length)
            .map { toHammingCodeValue(it) }
            .joinToString("")
            .let(::EncodedString)
    }

    /**
     * A method for calculating parity bits at given indexes for a given message.
     */
    fun getParityBit(codeWordIndex: Int): String {
        return parityIndicesSequence(codeWordIndex, codewordSize(length))
            .map { getDataBit(it).toInt() }
            .reduce { a, b -> a xor b }
            .toString()
    }

    /**
     * A method for calculating data bits at given indexes for a given message.
     */
    fun getDataBit(ind: Int): String {
        val binaryString = Integer.toBinaryString(ind)
        val dataBitPosition = ind - binaryString.length
        return this[dataBitPosition].toString()
    }

    private fun toHammingCodeValue(index: Int): String {
        return when ((index + 1).isPowerOfTwo()) {
            true -> getParityBit(index)
            false -> getDataBit(index)
        }
    }
}
