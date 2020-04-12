@file:JvmName("TreeBuilder")
package uzhnu.edu.toie.huffman

fun buildATree(table: FrequencyTable): SubTree.NonEmpty {
    val subTree = table.firstTree()
    return buildATreeR(subTree).fix()
}

tailrec fun buildATreeR(previousTree: SubTree): SubTree {
    return when (previousTree) {
        is SubTree.Empty -> previousTree
        is SubTree.NonEmpty -> {
            val previousMaxFrequency = previousTree.fix().table.maxWeight

            val newTree = previousTree.nextSubTree()
            val newTreeHead = newTree.fix().head
            val newMaxFrequency = newTree.fix().table.maxWeight
            val newWeight = newTreeHead.weight

            if (newWeight > previousMaxFrequency && newMaxFrequency != 0) {
                val leftTree: SubTree.NonEmpty = buildATree(newTree.fix().table)
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
