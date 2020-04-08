package uzhnu.edu.bai.dialog

interface Comparison {
    fun compare(string: String): String?
}

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

class ByRegEx(
        private val regEx: Regex,
        private val response: String
) : Comparison {
    override fun compare(string: String): String? {
        val matchResult = regEx.findAll(string).toList()
        return if (matchResult.isEmpty()) {
            null
        } else {
            response
        }
    }
}