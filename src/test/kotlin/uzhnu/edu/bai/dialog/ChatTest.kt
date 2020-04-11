package uzhnu.edu.bai.dialog

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object ChatTest : Spek({
    describe("chat with the user") {
        it("allows to combine multiple rules") {
            val chat = chat {
                this exactly "hello" respond "world"
                contains("good", "bad") respond "Well we all have something bad or good in our lives."
                this forStatement "My name is (\\w+)" respond "Nice to meet you %s"
            }

            chat respond "hello" shouldBeEqualTo "world"
            chat respond "My day was not that bad!" shouldBeEqualTo "Well we all have something bad or good in our lives."
            chat respond "My name is Bilbo" shouldBeEqualTo "Nice to meet you Bilbo"
        }

        it("buying paper by phone") {
            val chat = chat {
                contains("hello", "Hello", "good day") respond "Hello Dunder Mifland listening, how can I help you?"
                this forStatement "I would like to buy (\\w*)[ ]*(\\w*) of paper" respond "Yes we can sell you %s of paper"
                this exactly "No thank you!" respond "Have a good day"
            }

            chat respond "Hello" shouldBeEqualTo "Hello Dunder Mifland listening, how can I help you?"
            chat respond "I would like to buy 100 blocks of paper" shouldBeEqualTo "Yes we can sell you 100 blocks of paper"
            chat respond "No thank you!" shouldBeEqualTo "Have a good day"
        }
    }
})
