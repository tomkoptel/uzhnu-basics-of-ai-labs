package uzhnu.edu.toie.huffman

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object HuffmanTest2 : Spek({
    describe("a") {
        it("b") {
            val frequencies: List<Pair<Char, Int>> = listOf(
                'A' to 10,
                'E' to 15,
                'I' to 12,
                'S' to 3,
                'T' to 4,
                'P' to 13,
                '0' to 1
            )
            val table = FrequencyTable.createWithNotSorted(frequencies)
            println(buildATree(table))
        }
    }
})

fun buildATree(table: FrequencyTable): SubTree2.NonEmpty {
    val subTree = table.firstTree()
    return buildATreeR(subTree).fix()
}

tailrec fun buildATreeR(subTree: SubTree2): SubTree2 {
    return when (subTree) {
        is SubTree2.Empty -> subTree
        is SubTree2.NonEmpty -> buildATreeR(subTree.nextSubTree())
    }
}

sealed class SubTree2 {
    fun fix(): NonEmpty {
        return when (this) {
            is NonEmpty -> this
            is Empty -> this.head
        }
    }

    data class NonEmpty(
        val head: ParentNode,
        val table: FrequencyTable
    ) : SubTree2() {
        fun nextSubTree(): SubTree2 {
            return when (table.isEmpty()) {
                true -> Empty(this)
                false -> buildNewSubtree()
            }
        }

        private fun buildNewSubtree(): NonEmpty {
            val (newTable, lowestItem) = table.lowestItem()
            val newHead = ParentNode(
                left = lowestItem,
                right = head,
                weight = lowestItem.frequency + head.weight
            )
            return NonEmpty(head = newHead, table = newTable)
        }
    }

    data class Empty(val head: NonEmpty) : SubTree2()
}

data class FrequencyTable(
    val table: List<Pair<Char, Int>>,
    val maxFrequency: Int
) {
    fun firstTree(): SubTree2.NonEmpty {
        val leftNode = table[0].toNode()
        val rightNode = table[1].toNode()
        val headNode = ParentNode(
            left = leftNode,
            right = rightNode,
            weight = leftNode.frequency + rightNode.frequency
        )
        val minusTwoElements = table.subList(2, table.size)
        val newTable = createWithSorted(minusTwoElements)
        return SubTree2.NonEmpty(head = headNode, table = newTable)
    }

    fun isEmpty() = table.isEmpty()

    fun lowestItem(): Pair<FrequencyTable, ChildNode> {
        val element = table.first().toNode()
        val withoutFirst = table.subList(1, table.size)
        return createWithSorted(withoutFirst) to element
    }

    companion object {
        fun createWithSorted(frequencies: List<Pair<Char, Int>>): FrequencyTable {
            val maxFrequency = computeMaxFrequency(frequencies)
            return FrequencyTable(frequencies, maxFrequency)
        }

        fun createWithNotSorted(frequencies: List<Pair<Char, Int>>): FrequencyTable {
            val priorityPairs = frequencies.sortedBy { (_, frequency) -> frequency }
            val maxFrequency = computeMaxFrequency(priorityPairs)
            return FrequencyTable(priorityPairs, maxFrequency)
        }

        private fun computeMaxFrequency(frequencies: List<Pair<Char, Int>>): Int {
            return frequencies.map { (_, f) -> f }.max() ?: 0
        }
    }
}
