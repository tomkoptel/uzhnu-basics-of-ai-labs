package uzhnu.edu.tdspc

class PhilosopherMonitor(
    private val rightFork: Any,
    private val leftFork: Any
) : Philosopher() {
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
