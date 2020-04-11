package uzhnu.edu.bai.dialog

import org.junit.Test

internal class ContainsComparisonTest {
    private val response = "Усім хочеться заробляти більше."
    private val comparison = ContainsComparison(sampleList = listOf("влаштовує", "зарплата"), response = response)

    @Test
    fun `compares by collection 1`() {
        val question = "Скажіть, будь ласка, чи влаштовує Вас зарплата?"
        assert(comparison.compare(question) == response)
    }

    @Test
    fun `compares by collection 2`() {
        val question = "Яка у вас зарплата?"
        assert(comparison.compare(question) == response)
    }
}