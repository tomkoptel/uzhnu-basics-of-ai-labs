package uzhnu.edu.toie.huffman

import java.util.*

class WeightTable(
    val queue: Queue<Node>
) {

    fun buildTreeHead() : Node {
        val internalQueue = PriorityQueue(Node.Comparator).apply { addAll(queue) }

        while (internalQueue.size != 1) {
            val left = internalQueue.poll()
            val right = internalQueue.poll()
            internalQueue.offer(left + right)
        }

        return internalQueue.poll()
    }

    companion object {
        fun create(frequencies: List<Pair<Char, Int>>): WeightTable {
            val queue = PriorityQueue(Node.Comparator)
            frequencies.forEach { queue.add(it.toNode()) }
            return WeightTable(queue)
        }
    }
}
