package uzhnu.edu.toie.huffman

import java.util.*

class WeightTable(
    val queue: Queue<Node>
) {
    fun buildBinaryDictionary(): Map<Char, String> {
        val dictionary: MutableMap<Char, String> = mutableMapOf()
        buildR(dictionary, buildTreeHead(), "")
        return dictionary.toMap()
    }

    private tailrec fun buildR(
        dictionary: MutableMap<Char, String>,
        tree: Node,
        suffix: String
    ) {
        when (tree) {
            is ParentNode -> {
                @Suppress("NON_TAIL_RECURSIVE_CALL")
                buildR(dictionary, tree.left, suffix + "0")
                buildR(dictionary, tree.right, suffix + "1")
            }
            is ChildNode -> {
                dictionary[tree.value] = suffix
            }
        }
    }

    fun buildTreeHead(): ParentNode {
        val internalQueue = PriorityQueue(Node.Ascending).apply { addAll(queue) }

        while (true) {
            val left = internalQueue.poll()
            val right = internalQueue.poll()
            internalQueue.offer(left + right)

            if (internalQueue.size == 1) {
                val head = internalQueue.poll()
                if (head is ParentNode) {
                    return head
                }
            }
        }
    }

    companion object {
        fun create(frequencies: Map<Char, Int>): WeightTable {
            require(frequencies.isNotEmpty()) {
                "We can not work with empty map of character frequencies."
            }
            val queue = PriorityQueue(Node.Ascending)
            frequencies.forEach { (char, weight) ->
                queue.add(ChildNode(value = char, weight = weight))
            }
            return WeightTable(queue)
        }
    }
}
