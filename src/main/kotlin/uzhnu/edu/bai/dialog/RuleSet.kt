package uzhnu.edu.bai.dialog

class RuleSet {
    private val matchers: MutableList<Comparison> = mutableListOf<Comparison>()

    fun respond(question: String) {
        for (matcher in matchers) {
            val response = matcher.compare(question)
            if (response != null) {
                println(response)
                break
            }
        }
    }

    infix fun exactly(value: String): ExactMatch {
        return ExactMatch(matchers, value)
    }

    fun contains(vararg value: String): ContainsMatch {
        return ContainsMatch(matchers, value.toList())
    }

    infix fun forStatement(value: String): RegExMatch {
        return RegExMatch(matchers, value.toRegex())
    }

    class ExactMatch(
        private val matchers: MutableList<Comparison>,
        private val match: String
    ) {
        infix fun respond(response: String) {
            matchers.add(ExactComparison(match, response))
        }
    }

    class ContainsMatch(
        private val matchers: MutableList<Comparison>,
        private val match: List<String>
    ) {
        infix fun respond(response: String) {
            matchers.add(ContainsComparison(match, response))
        }
    }

    class RegExMatch(
        private val matchers: MutableList<Comparison>,
        private val match: Regex
    ) {
        infix fun respond(response: String) {
            matchers.add(ByRegEx(match, response))
        }
    }
}