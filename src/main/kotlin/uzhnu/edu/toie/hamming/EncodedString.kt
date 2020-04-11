package uzhnu.edu.toie.hamming

/**
 * Strings represents encoded messages
 */
data class EncodedString(private val value: String) {
    operator fun get(index: Int): Char = value[index]
    val length get() = value.length

    fun decode() : BinaryString {
        return indexesOfInvalidParityBits(this).let { indexesOfParityBits ->
            when (indexesOfParityBits.isEmpty()) {
                true -> this // valid case
                false -> this.withBitFlippedAt(indexesOfParityBits.sum() - 1)
            }
        }.let { stripHammingMetadata() }
    }

    fun withBitFlippedAt(index: Int): EncodedString {
        return this[index].toString().toInt()
            .let { this.value.replaceRange(index, index + 1, ((it + 1) % 2).toString()) }
            .let(::EncodedString)
    }

    fun stripHammingMetadata(): BinaryString {
        return value.asSequence()
            .filterIndexed { i, _ -> (i + 1).isPowerOfTwo().not() }
            .joinToString("")
            .let(::BinaryString)
    }

    fun indexesOfInvalidParityBits(input: EncodedString): List<Int> {
        return generateSequence(1) { it * 2 }
            .takeWhile { it < input.length }
            .map { toValidationResult(it) }
            .filter { !it.second }
            .map { it.first }
            .toList()
    }

    fun toValidationResult(index: Int): Pair<Int, Boolean> =
        parityIndicesSequence(index - 1, length)
            .map { v -> this[v].toBinaryInt() }
            .fold(this[index - 1].toBinaryInt()) { a, b -> a xor b }
            .let { r -> index to (r == 0) }
}

