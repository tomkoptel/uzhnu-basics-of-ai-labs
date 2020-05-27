package uzhnu.edu.informatics

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TasksTest : Spek({
    describe("Дано масив M, розмірністю 11. Знайти найбільший елемент масиву та його номер.") {
        it ("найбільше 4") {
            Tasks.findMax(intArrayOf(1, 2, 3, 4))
        }
    }
    describe("Дано масив M, розмірністю 11. Знайти найбільший елемент масиву та його номер.") {
        it ("подвійна сумма 20") {
            Tasks.doubleSum(intArrayOf(1, 2, 3, 4))
        }
    }
})
