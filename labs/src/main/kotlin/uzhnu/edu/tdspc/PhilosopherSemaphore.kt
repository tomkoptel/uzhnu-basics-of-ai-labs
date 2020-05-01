package uzhnu.edu.tdspc

class PhilosopherSemaphore(
    private val rightFork: Fork,
    private val leftFork: Fork
) : Philosopher() {
    override fun run() {
        try {
            while (true) {
                // thinking
                doAction("${System.nanoTime()}: Thinking")
                if (leftFork.isFree()) {
                    doAction("${System.nanoTime()}: Picked up left fork")
                    leftFork.pick()

                    if (rightFork.isFree()) {
                        // eating
                        doAction("${System.nanoTime()}: Picked up right fork - eating")
                        rightFork.pick()

                        rightFork.putDown()
                        doAction("${System.nanoTime()}: Put down right fork")
                    }

                    // Back to thinking
                    leftFork.putDown()
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
