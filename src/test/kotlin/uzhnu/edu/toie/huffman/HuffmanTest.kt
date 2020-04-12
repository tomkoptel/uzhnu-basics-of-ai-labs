package uzhnu.edu.toie.huffman

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object HuffmanTest2 : Spek({
    describe("buildATree") {
        val frequencies: List<Pair<Char, Int>> = listOf(
            'A' to 10,
            'E' to 15,
            'I' to 12,
            'S' to 3,
            'T' to 4,
            'P' to 13,
            '0' to 1
        )

        it("for frequencies=$frequencies") {
            val table = FrequencyTable.createWithNotSorted(frequencies)

            val expected = SubTree2.NonEmpty(
                head = ParentNode(weight = 58,
                    left = ParentNode(
                        weight = 25,
                        left = ChildNode(value = 'I', weight = 12),
                        right = ChildNode(value = 'P', weight = 13)
                    ),
                    right = ParentNode(
                        weight = 33,
                        left = ParentNode(
                            weight = 18,
                            left = ChildNode(value = 'A', weight = 10),
                            right = ParentNode(
                                weight = 8,
                                left = ChildNode(value = 'T', weight = 4),
                                right = ParentNode(
                                    weight = 4,
                                    left = ChildNode(value = '0', weight = 1),
                                    right = ChildNode(value = 'S', weight = 3)))
                        ),
                        right = ChildNode(value = 'E', weight = 15)
                    )
                ),
                table = FrequencyTable(table = emptyList(), maxFrequency = 0)
            )
            buildATree(table) shouldBeEqualTo expected
        }
    }
})

fun buildATree(table: FrequencyTable): SubTree2.NonEmpty {
    val subTree = table.firstTree()
    return buildATreeR(subTree).fix()
}

tailrec fun buildATreeR(previousTree: SubTree2): SubTree2 {
    return when (previousTree) {
        is SubTree2.Empty -> previousTree
        is SubTree2.NonEmpty -> {
            val previousMaxFrequency = previousTree.maxFrequency

            val newTree = previousTree.nextSubTree()
            val newTreeHead = newTree.fix().head
            val newMaxFrequency = newTree.maxFrequency
            val newWeight = newTreeHead.weight

            if (newWeight > previousMaxFrequency && newMaxFrequency != 0) {
                val leftTree: SubTree2.NonEmpty = buildATree(newTree.table2)
                val leftTreeHead = leftTree.head
                if (leftTreeHead.weight > newMaxFrequency) {
                    val parentNode = leftTree.head
                    val l = parentNode.left
                    val r = parentNode.right
                    val combinedWeight = newTreeHead.weight + l.weight

                    val x = ParentNode(
                        left = newTreeHead,
                        right = l,
                        weight = combinedWeight
                    )
                    leftTree.copy(
                        head = ParentNode(
                            left = r,
                            right = x,
                            weight = r.weight + x.weight
                        )
                    )
                } else {
                    leftTree
                }
            } else {
                buildATreeR(newTree)
            }
        }
    }
}

sealed class SubTree2 {
    fun fix(): NonEmpty {
        return when (this) {
            is NonEmpty -> this
            is Empty -> this.head
        }
    }

    val maxFrequency get() = fix().table.maxFrequency

    val table2 get() = fix().table

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
                weight = lowestItem.weight + head.weight
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
            weight = leftNode.weight + rightNode.weight
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
