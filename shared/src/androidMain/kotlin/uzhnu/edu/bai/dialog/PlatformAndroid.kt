package uzhnu.edu.bai.dialog

actual fun currentTimeMillis(): Long = System.currentTimeMillis()

internal actual fun printThrowable(t: Throwable) {
    t.printStackTrace()
}
