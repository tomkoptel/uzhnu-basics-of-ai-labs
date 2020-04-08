package uzhnu.edu.bai.dialog

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
