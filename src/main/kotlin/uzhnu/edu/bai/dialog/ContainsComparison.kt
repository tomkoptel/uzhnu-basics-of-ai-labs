package uzhnu.edu.bai.dialog

class ContainsComparison(
        private val sampleList: List<String>,
        private val response: String
) : Comparison {
    override fun compare(string: String): String? {
        val matches = sampleList.map { string.contains(it, ignoreCase = true) }.reduceRight { a, b -> a || b }
        return if (matches) {
            response
        } else {
            null
        }
    }
}
