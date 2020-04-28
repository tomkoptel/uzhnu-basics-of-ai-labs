package uzhnu.edu.bai.dialog

internal fun readString(): String = generateSequence { System.`in`.read().toChar() }
    .dropWhile { isWhiteSpace(it) }.takeWhile { !isWhiteSpace(it) }.joinToString("")

internal fun isWhiteSpace(c: Char) = c in " \r\n\t"
