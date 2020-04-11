package uzhnu.edu.bai.dialog

/**
 * We’ll need a method for calculating the encoded codeword size
 * for a given message. In this case, we simply find the lowest
 * number of parity pairs that can cover the given message.
 */
fun codewordSize(msgLength: Int): Int {
    val parityPair = generateSequence(2) { it + 1 }
        .first { r ->
            val right = msgLength + r + 1
            val left = 1 shl r
            right <= left
        }
    return parityPair + msgLength
}

fun getHammingCodewordIndices(msgLength: Int) = generateSequence(0, Int::inc)
    .take(codewordSize(msgLength))

fun parityIndicesSequence(start: Int, endEx: Int): Sequence<Int> {
    val result = generateSequence(start) { it + 1 }
        .take(endEx - start)
        .filterIndexed { index, _ ->
            val startInc = start + 1
            val startIncMultiplied = startInc * 2
            val mod = index % startIncMultiplied
            mod < startInc
        }
    return result.drop(1)
}

internal fun Int.isPowerOfTwo() = this != 0 && this and this - 1 == 0
