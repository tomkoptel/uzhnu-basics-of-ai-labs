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

/**
 * Next, we’ll need a method for calculating parity and data bits at given indexes for a given message:
 */
fun getParityBit(codeWordIndex: Int, msg: BinaryString) {
    parityIndicesSequence(codeWordIndex, codewordSize(msg.length))
        .map { getDataBit(it, msg).toInt() }
        .reduce { a, b -> a xor b }
        .toString()
}

fun getDataBit(ind: Int, input: BinaryString): String {
    val binaryString = Integer.toBinaryString(ind)
    val dataBitPosition = ind - binaryString.length
    return input[dataBitPosition].toString()
}

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
