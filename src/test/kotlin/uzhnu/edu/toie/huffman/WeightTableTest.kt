package uzhnu.edu.toie.huffman

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object WeightTableTest : Spek({
    describe("create") {
        it ("should build priority queue based on weights") {
            val frequencies = listOf(
                'E' to 15,
                'S' to 3
            )
            val queue = WeightTable.create(frequencies).queue
            queue.poll() shouldBeEqualTo ChildNode(value = 'S', weight = 3)
            queue.poll() shouldBeEqualTo ChildNode(value = 'E', weight = 15)
        }
    }

    describe("buildTreeHead") {
        it ("should build a tree 18 -> (E, 15), (S, 3)") {
            val frequencies = listOf(
                'E' to 15,
                'S' to 3
            )
            val queue = WeightTable.create(frequencies)
            val treeHead = queue.buildTreeHead() as ParentNode
            treeHead shouldBeEqualTo ParentNode(
                weight = 18,
                left = ChildNode(value = 'S', weight = 3),
                right = ChildNode(value = 'E', weight = 15)
            )
        }
    }
})

