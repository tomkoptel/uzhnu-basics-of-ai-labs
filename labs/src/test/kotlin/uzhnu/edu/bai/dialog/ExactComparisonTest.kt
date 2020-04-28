package uzhnu.edu.bai.dialog

import org.junit.Test

internal class ExactComparisonTest {
    @Test
    fun `should match exactly`() {
        val response = "Із задоволенням, я до Ваших послуг."
        val sample = "Доброго дня, пропонуємо взяти участь у опитуванні"
        assert(ExactComparison(sample = sample, response = response).compare(sample) == response)
    }
}
