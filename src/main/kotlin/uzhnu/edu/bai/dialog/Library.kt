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
    return input[ind - Integer.toBinaryString(ind).length].toString()
}

fun parityIndicesSequence(start: Int, endEx: Int): Sequence<Int> {
    return generateSequence(start) { it + 1 }
        .take(endEx - start)
        .filterIndexed { index, _ ->
            index % ((2 * (start + 1))) < start + 1
        }
        .drop(1) // ignore the initial parity bit
}
