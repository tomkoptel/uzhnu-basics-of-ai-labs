package uzhnu.edu.tdspc

import java.util.concurrent.TimeUnit

class Dinner(
    private val philosopher: (rightFork: Fork, leftFork: Fork) -> Philosopher
) {
    fun perform() {
        val philosophers = arrayOfNulls<Philosopher>(size = 5)
        val forks = (0 until 5).map { Fork() }
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
                philosophers[i] = philosopher(rightFork, leftFork)
            } else {
                philosophers[i] = philosopher(leftFork, rightFork)
            }
            philosophers[i] = PhilosopherMonitor(leftFork, rightFork)
            val t = Thread(philosophers[i], "Philosopher " + (i + 1))
            t.start()
            threads.add(t)
        }

        threads.forEach { it.join(TimeUnit.MILLISECONDS.toMillis(500)) }
    }
}
