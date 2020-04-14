package uzhnu.edu.tdspc

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.concurrent.TimeUnit

object Lab5Test : Spek({
    describe("philosophers dinning using monitors") {
        it("should not run in the dead lock") {
            val philosophers = arrayOfNulls<Philosopher>(size = 5)
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
                    philosophers[i] = Philosopher(rightFork, leftFork)
                } else {
                    philosophers[i] = Philosopher(leftFork, rightFork)
                }
                philosophers[i] = Philosopher(leftFork, rightFork)
                val t = Thread(philosophers[i], "Philosopher " + (i + 1))
                t.start()
                threads.add(t)
            }

            threads.forEach { it.join(TimeUnit.MILLISECONDS.toMillis(100)) }
        }
    }
})

class Philosopher(
    private val rightFork: Any,
    private val leftFork: Any
) : Runnable {
    override fun run() {
        try {
            while (true) {
                // thinking
                doAction("${System.nanoTime()}: Thinking")
                synchronized(leftFork) {
                    doAction("${System.nanoTime()}: Picked up left fork")

                    synchronized(rightFork) {
                        // eating
                        doAction("${System.nanoTime()}: Picked up right fork - eating")
                        doAction("${System.nanoTime()}: Put down right fork")
                    }

                    // Back to thinking
                    doAction("${System.nanoTime()}: Put down left fork. Back to thinking")
                }
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            return
        }
    }

    private fun doAction(action: String) {
        println(Thread.currentThread().name + " " + action)
        Thread.sleep((Math.random() * 100).toLong())
    }
}
