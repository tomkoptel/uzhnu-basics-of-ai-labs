package uzhnu.edu.bai.dialog

fun chat(build: Chat.() -> Unit) : Chat {
    return Chat().apply(build)
}

class Chat {
    private val matchers: MutableList<Comparison> = mutableListOf<Comparison>()

    infix fun respond(question: String) : String? {
        for (matcher in matchers) {
            val response = matcher.compare(question)
            if (response != null) {
                return response
            }
        }
        return null
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
