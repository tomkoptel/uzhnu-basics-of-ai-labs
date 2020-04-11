package uzhnu.edu.bai.dialog

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class LibraryTest : Spek({
    describe("codewordSize") {
        listOf(
            1 to 3,
            2 to 5,
            3 to 6,
            4 to 7,
            5 to 9,
            6 to 10,
            7 to 11
        ).forEach { (msgLength, codewordSize) ->
            it("for msgLength=$msgLength should return codewordSize=$codewordSize") {
                codewordSize(msgLength) shouldBeEqualTo codewordSize
            }
        }
    }

    describe("parityIndicesSequence") {
        listOf(
            (0 to 1) to emptyList(),
            (0 to 2) to emptyList(),
            (0 to 4) to listOf(2),
            (0 to 5) to listOf(2, 4),
            (0 to 6) to listOf(2, 4),
            (0 to 7) to listOf(2, 4, 6),
            (0 to 8) to listOf(2, 4, 6),
            (0 to 9) to listOf(2, 4, 6, 8),
            (0 to 10) to listOf(2, 4, 6, 8),
            (0 to 11) to listOf(2, 4, 6, 8, 10),
            (0 to 12) to listOf(2, 4, 6, 8, 10),
            (0 to 13) to listOf(2, 4, 6, 8, 10, 12),
            (0 to 14) to listOf(2, 4, 6, 8, 10, 12),
            (0 to 15) to listOf(2, 4, 6, 8, 10, 12, 14),
            (0 to 16) to listOf(2, 4, 6, 8, 10, 12, 14),
            (0 to 17) to listOf(2, 4, 6, 8, 10, 12, 14, 16),
            (1 to 8) to listOf(2, 5, 6),
            (2 to 8) to listOf(3, 4),
            (3 to 8) to listOf(4, 5, 6),
            (4 to 8) to listOf(5, 6, 7)
        ).forEach { (indices, result) ->
            val (startIndex, endIndex) = indices
            it("for startIndex=$startIndex and endIndex=$endIndex should return result=$result") {
                val sequence = parityIndicesSequence(startIndex, endIndex)
                val actual = sequence.map { it }.toList()
                actual shouldBeEqualTo result
            }
        }
    }

    describe("isPowerOfTwo") {
        listOf(
            0 to false,
            1 to true,
            2 to true,
            4 to true,
            8 to true,
            16 to true,
            32 to true,
            64 to true,
            128 to true,
            256 to true
        ).forEach { (num, isPowerOfTwo) ->
            it("the num=$num isPowerOfTwo=$isPowerOfTwo") {
                num.isPowerOfTwo() shouldBeEqualTo isPowerOfTwo
            }
        }
    }
})
