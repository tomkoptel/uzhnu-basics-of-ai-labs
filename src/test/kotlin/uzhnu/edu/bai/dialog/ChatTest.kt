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

            chat.respond("hello") shouldBeEqualTo "world"
            chat.respond("My day was not that bad!") shouldBeEqualTo "Well we all have something bad or good in our lives."
            chat.respond("My name is Bilbo") shouldBeEqualTo "Nice to meet you Bilbo"
        }
    }
})
