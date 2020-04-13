package uzhnu.edu.toie.huffman

import java.util.*

class WeightTable(
    val queue: Queue<Node>
) {
    companion object {
        fun create(frequencies: List<Pair<Char, Int>>): WeightTable {
            val queue = PriorityQueue(Node.Comparator)
            frequencies.forEach { queue.add(it.toNode()) }
            return WeightTable(queue)
        }
    }
}
