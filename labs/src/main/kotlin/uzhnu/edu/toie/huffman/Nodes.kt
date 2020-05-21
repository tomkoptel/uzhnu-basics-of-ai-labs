package uzhnu.edu.toie.huffman

import java.util.Comparator as JavaComparator

/**
 * Клас нода яка презентує один лист нашого дерева.
 */
sealed class Node(open val weight: Int) {
    /**
     * JavaComparator реалізує порівняня двух нодів в порядку від найменшого до найбільшого.
     */
    object Ascending : JavaComparator<Node> {
        override fun compare(nodeA: Node, nodeB: Node): Int {
            return nodeA.weight.compareTo(nodeB.weight)
        }
    }

    /**
     * Об'єднує ноду з 'права += ліво' та створює батьківську ноду.
     */
    operator fun plus(right: Node) : Node {
        val left = this
        return ParentNode(
            left = left,
            right = right,
            weight = left.weight + right.weight
        )
    }
}

/**
 * Репрезентує ноду яка містить символ та вагу символа.
 */
data class ChildNode(
    val value: Char,
    override val weight: Int
) : Node(weight)

/**
 * Репрезентує ноду яка містить посилання дві ноди лівої та правої гілки.
 */
data class ParentNode(
    override val weight: Int,
    val left: Node,
    val right: Node
) : Node(weight)
