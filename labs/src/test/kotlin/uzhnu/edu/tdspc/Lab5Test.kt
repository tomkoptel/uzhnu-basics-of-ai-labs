package uzhnu.edu.tdspc

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object Lab5Test : Spek({
    describe("philosophers dinning using monitors") {
        it("should not run in the dead lock") {
            Dinner { rightFork, leftFork ->
                PhilosopherMonitor(rightFork, leftFork)
            }.perform()
        }
    }
    describe("philosophers dinning using semaphor") {
        it("should not run in the dead lock") {
            Dinner { rightFork, leftFork ->
                PhilosopherSemaphore(rightFork, leftFork)
            }.perform()
        }
    }
})
