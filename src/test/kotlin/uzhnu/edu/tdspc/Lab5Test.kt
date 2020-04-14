package uzhnu.edu.tdspc

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.concurrent.TimeUnit

object Lab5Test : Spek({
    describe("philosophers dinning using monitors") {
        it("should not run in the dead lock") {
            val philosophers = arrayOfNulls<PhilosopherMonitor>(size = 5)
            val forks = (0 until 5).map { Any() }
            val threads = mutableListOf<Thread>()

            for (i in philosophers.indices) {
                val leftFork = forks[i]
                val rightFork = forks[(i + 1) % forks.size]

                /**
                 * All Philosophers reach for their left fork first, except one who first reaches for his right fork.
                 * The primary reason for a deadlock is the circular wait condition where each process waits upon a
                 * resource that's being held by some other process. Hence, to avoid a deadlock situation we need to
                 * make sure that the circular wait condition is broken. There are several ways to achieve this, the
                 * simplest one being the follows:
                 */
                if (i == philosophers.size - 1) {
                    // The last philosopher picks up the right fork first
                    philosophers[i] = PhilosopherMonitor(rightFork, leftFork)
                } else {
                    philosophers[i] = PhilosopherMonitor(leftFork, rightFork)
                }
                philosophers[i] = PhilosopherMonitor(leftFork, rightFork)
                val t = Thread(philosophers[i], "Philosopher " + (i + 1))
                t.start()
                threads.add(t)
            }

            threads.forEach { it.join(TimeUnit.MILLISECONDS.toMillis(100)) }
        }
    }
})

