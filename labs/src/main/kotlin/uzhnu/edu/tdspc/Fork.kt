package uzhnu.edu.tdspc

import java.util.concurrent.Semaphore

class Fork {
    private val semaphore = Semaphore(1)

    fun pick() {
        semaphore.acquire()
    }

    fun putDown() {
        semaphore.release()
    }

    fun isFree(): Boolean {
        return semaphore.availablePermits() > 0
    }
}
