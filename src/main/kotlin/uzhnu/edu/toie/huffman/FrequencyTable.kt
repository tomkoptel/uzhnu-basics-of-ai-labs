package uzhnu.edu.toie.huffman

class FrequencyTable private constructor(
    val table: List<ChildNode>,
    val maxWeight: Int
) {
    fun firstTree(): SubTree.NonEmpty {
        val leftNode = table[0]
        val rightNode = table[1]
        val headNode = ParentNode(
            left = leftNode,
            right = rightNode,
            weight = leftNode.weight + rightNode.weight
        )
        val minusTwoElements = table.subList(2, table.size)
        val newTable = recompute(minusTwoElements)
        return SubTree.NonEmpty(head = headNode, table = newTable)
    }

    fun isEmpty() = table.isEmpty()

    fun lowestItem(): Pair<FrequencyTable, ChildNode> {
        val element = table.first()
        val withoutFirst = table.subList(1, table.size)
        return recompute(withoutFirst) to element
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FrequencyTable

        if (table != other.table) return false
        if (maxWeight != other.maxWeight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = table.hashCode()
        result = 31 * result + maxWeight
        return result
    }

    override fun toString(): String {
        return "FrequencyTable(table=$table, maxWeight=$maxWeight)"
    }

    companion object {
        val EMPTY = FrequencyTable(emptyList(), 0)

        fun recompute(nodes: List<ChildNode>): FrequencyTable {
            val maxFrequency = computeMaxWeight(nodes)
            return FrequencyTable(nodes, maxFrequency)
        }

        fun create(frequencies: List<Pair<Char, Int>>): FrequencyTable {
            val nodes = frequencies.sortedBy { (_, frequency) -> frequency }.map { it.toNode() }
            val maxFrequency = computeMaxWeight(nodes)
            return FrequencyTable(nodes, maxFrequency)
        }

        private fun computeMaxWeight(frequencies: List<ChildNode>): Int {
            return frequencies.map { it.weight }.max() ?: 0
        }
    }
}
