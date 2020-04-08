package uzhnu.edu.bai.dialog


fun main() {
    chat {
        this exactly "hello" respond "world"
        contains("good", "bad") respond "happens"
        this forStatement "hello (\\w)+" respond "Nice to meet you %s"
    }
}

fun chat(build: RuleSet.() -> Unit) {
    val resultSet = RuleSet().apply(build)
    val question = readString()
    println(resultSet.respond(question))
}

