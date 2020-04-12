package uzhnu.edu.toie.huffman

abstract class Node(open val weight: Int)

data class ChildNode(
    val value: Char,
    override val weight: Int
) : Node(weight)

data class ParentNode(
    override val weight: Int,
    val left: Node,
    val right: Node
) : Node(weight)

fun Pair<Char, Int>.toNode() = ChildNode(first, second)
