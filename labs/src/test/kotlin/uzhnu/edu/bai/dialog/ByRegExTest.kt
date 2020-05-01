package uzhnu.edu.bai.dialog

import org.junit.Test

internal class ByRegExTest {
    @Test
    fun substitution() {
        val comparison = ByRegEx(
            regEx = "hello (\\w+)".toRegex(),
            response = "Hello, %s nice to see you"
        )
        val response = comparison.compare("hello Kitty")
        assert(response == "Hello, Kitty nice to see you")
    }
}
