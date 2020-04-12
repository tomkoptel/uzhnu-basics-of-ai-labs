package uzhnu.edu.toie.huffman

typealias HuffmanTree = SubTree.NonEmpty

sealed class SubTree {
    fun fix(): NonEmpty {
        return when (this) {
            is NonEmpty -> this
            is Empty -> this.head
        }
    }

    data class NonEmpty(
        val head: ParentNode,
        val table: FrequencyTable
    ) : SubTree() {
        fun nextSubTree(): SubTree {
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

    data class Empty(val head: NonEmpty) : SubTree()
}
