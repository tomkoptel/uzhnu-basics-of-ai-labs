package uzhnu.edu.toie.huffman

interface Node

data class ChildNode(
    val value: Char,
    val frequency: Int
) : Node

data class ParentNode(
    val weight: Int,
    val left: Node,
    val right: Node
) : Node

fun Pair<Char, Int>.toNode() = ChildNode(first, second)
