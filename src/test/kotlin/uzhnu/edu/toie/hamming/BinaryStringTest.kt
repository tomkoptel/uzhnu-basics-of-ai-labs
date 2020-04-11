package uzhnu.edu.toie.hamming

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object BinaryStringTest : Spek({
    describe("getDataBit") {
        it("for word 0010") {
            val word = BinaryString("0010")

            word.getDataBit(ind = 2) shouldBeEqualTo "0"
            word.getDataBit(ind = 3) shouldBeEqualTo "0"
            word.getDataBit(ind = 4) shouldBeEqualTo "0"
            word.getDataBit(ind = 5) shouldBeEqualTo "1"
            word.getDataBit(ind = 6) shouldBeEqualTo "0"
        }

        it("for word 11011") {
            val word = BinaryString("11011")

            word.getDataBit(ind = 2) shouldBeEqualTo "1"
            word.getDataBit(ind = 3) shouldBeEqualTo "1"
            word.getDataBit(ind = 4) shouldBeEqualTo "1"
            word.getDataBit(ind = 5) shouldBeEqualTo "0"
            word.getDataBit(ind = 6) shouldBeEqualTo "1"
            word.getDataBit(ind = 7) shouldBeEqualTo "1"
        }
    }

    describe("getParityBit") {
        it("should calculate parity and data bits at given indexes") {
            val word = BinaryString("0100")

            word.getParityBit(0) shouldBeEqualTo "1"
            word.getParityBit(1) shouldBeEqualTo "0"
            word.getParityBit(2) shouldBeEqualTo "0"
            word.getParityBit(3) shouldBeEqualTo "1"
            word.getParityBit(4) shouldBeEqualTo "0"
            word.getParityBit(5) shouldBeEqualTo "0"
        }
    }

    describe("encode") {
        listOf(
            "1" to "111",
            "01" to "10011",
            "11" to "01111",
            "1001000" to "00110010000",
            "1100001" to "10111001001",
            "1101101" to "11101010101",
            "1101001" to "01101011001",
            "1101110" to "01101010110",
            "1100111" to "01111001111",
            "0100000" to "10011000000",
            "1100011" to "11111000011",
            "1101111" to "10101011111",
            "1100100" to "11111001100",
            "1100101" to "00111000101",
            "10011010" to "011100101010",
            "0" to "000",
            "00" to "00000",
            "000" to "000000",
            "0000" to "0000000",
            "00000" to "000000000"
        ).forEach { (binaryWord, encodeWord) ->
            it ("should encode '$binaryWord' to '$encodeWord'") {
                BinaryString(binaryWord).encode() shouldBeEqualTo EncodedString(encodeWord)
            }
        }
    }
})
