package uzhnu.edu.tdspc

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object Lab3Test : Spek({
    describe("a") {
        it ("b") {
            val array1 = intArrayOf(1, 2, 3)
            val array2 = intArrayOf(3, 4, 1)

            var numOfCommon = 0
            array1.forEach { el1 ->
                array2.forEach{ el2 ->
                    if (el1 == el2) {
                        numOfCommon++
                    }
                }
            }

            println(numOfCommon)
        }
    }
})

