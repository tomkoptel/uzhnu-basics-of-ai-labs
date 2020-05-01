package uzhnu.edu.toie.huffman

import java.util.Comparator as JavaComparator

sealed class Node(open val weight: Int) {
    object Ascending : JavaComparator<Node> {
        override fun compare(nodeA: Node, nodeB: Node): Int {
            return nodeA.weight.compareTo(nodeB.weight)
        }
    }

    operator fun plus(right: Node) : Node {
        val left = this
        return ParentNode(
            left = left,
            right = right,
            weight = left.weight + right.weight
        )
    }
}

data class ChildNode(
    val value: Char,
    override val weight: Int
) : Node(weight)

data class ParentNode(
    override val weight: Int,
    val left: Node,
    val right: Node
) : Node(weight)
