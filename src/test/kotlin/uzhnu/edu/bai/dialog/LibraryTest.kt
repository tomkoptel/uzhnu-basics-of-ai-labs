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
            it ("for msgLength=$msgLength should return codewordSize=$codewordSize") {
                codewordSize(msgLength) shouldBeEqualTo codewordSize
            }
        }
    }
})
