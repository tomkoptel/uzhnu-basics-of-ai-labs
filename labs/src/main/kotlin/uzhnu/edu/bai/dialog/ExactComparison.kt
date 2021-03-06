package uzhnu.edu.bai.dialog

class ExactComparison(
        private val sample: String,
        private val response: String
) : Comparison {
    override fun compare(string: String): String? {
        return if (sample == string) {
            return response
        } else {
            null
        }
    }
}
