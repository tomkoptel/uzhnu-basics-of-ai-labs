package uzhnu.edu.bai.dialog

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object EncodedStringTest : Spek({
    describe("decode") {
        describe("correct codes") {
            listOf(
                "111" to "1",
                "10011" to "01",
                "01111" to "11",
                "00110010000" to "1001000",
                "10111001001" to "1100001",
                "11101010101" to "1101101",
                "01101011001" to "1101001",
                "01101010110" to "1101110",
                "01111001111" to "1100111",
                "10011000000" to "0100000",
                "11111000011" to "1100011",
                "10101011111" to "1101111",
                "11111001100" to "1100100",
                "00111000101" to "1100101",
                "011100101010" to "10011010",
                "000" to "0",
                "00000" to "00",
                "000000" to "000",
                "0000000" to "0000",
                "000000000" to "00000"
            ).forEach { (binaryWord, encodeWord) ->
                it("should encode '$binaryWord' to '$encodeWord'") {
                    EncodedString(binaryWord).decode() shouldBeEqualTo BinaryString(encodeWord)
                }
            }
        }

        describe("incorrect codes") {
            listOf(
                "011" to "1", // flipped at 0(1)
                "101" to "1", // flipped at 1(2)
                "100" to "0", // flipped at 0(1)
                "010" to "0", // flipped at 1(2)
                "11011" to "01", // flipped at 1(2)
                "10001" to "01"  // flipped at 3(4)
            ).forEach { (binaryWord, encodeWord) ->
                it("should encode '$binaryWord' to '$encodeWord'") {
                    EncodedString(binaryWord).decode() shouldBeEqualTo BinaryString(encodeWord)
                }
            }
        }
    }
})
