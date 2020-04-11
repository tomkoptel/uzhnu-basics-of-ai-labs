package uzhnu.edu.bai.dialog

/**
 * We’ll need a method for calculating the encoded codeword size
 * for a given message. In this case, we simply find the lowest
 * number of parity pairs that can cover the given message.
 */
fun codewordSize(msgLength: Int) : Int {
    val first = generateSequence(2) { it + 1 }
        .first { r -> msgLength + r + 1 <= (1 shl r) }
    return first + msgLength
}
