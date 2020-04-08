package uzhnu.edu.bai.dialog

import org.junit.Test


internal class ByRegExTest {
    @Test
    fun name() {
        val comparison = ByRegEx(
                regEx = "hello (\\w+)".toRegex(),
                response = "Hello, nice to see you"
        )
        print(comparison.compare("hello Kitty"))
    }
}