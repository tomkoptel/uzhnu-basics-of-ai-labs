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
     * Next, we’ll need a method for calculating parity and data bits at given indexes for a given message:
     */
    fun getParityBit(codeWordIndex: Int): String {
        return parityIndicesSequence(codeWordIndex, codewordSize(length))
            .map { getDataBit(it).toInt() }
            .reduce { a, b -> a xor b }
            .toString()
    }

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
