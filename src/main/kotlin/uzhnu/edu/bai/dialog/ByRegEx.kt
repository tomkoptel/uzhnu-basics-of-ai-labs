package uzhnu.edu.bai.dialog

class ByRegEx(
    private val regEx: Regex,
    private val response: String
) : Comparison {
    override fun compare(string: String): String? {
        val matchResult = regEx.findAll(string).toList()
        return if (matchResult.isNotEmpty()) {
            val result = matchResult.first()
            val matching = result.groupValues.lastOrNull()
            String.format(response, matching)
        } else {
            null
        }
    }
}
