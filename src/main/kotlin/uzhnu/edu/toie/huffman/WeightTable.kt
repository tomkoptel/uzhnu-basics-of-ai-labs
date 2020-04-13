package uzhnu.edu.toie.huffman

import java.util.*

class WeightTable(
    val queue: Queue<Node>
) {
    fun buildTreeHead() : Node {
        val internalQueue = PriorityQueue(Node.Ascending).apply { addAll(queue) }

        while (internalQueue.size != 1) {
            val left = internalQueue.poll()
            val right = internalQueue.poll()
            internalQueue.offer(left + right)
        }

        return internalQueue.poll()
    }

    companion object {
        fun create(frequencies: Map<Char, Int>): WeightTable {
            val queue = PriorityQueue(Node.Ascending)
            frequencies.forEach { (char, weight) ->
                queue.add(ChildNode(value = char, weight = weight))
            }
            return WeightTable(queue)
        }
    }
}
