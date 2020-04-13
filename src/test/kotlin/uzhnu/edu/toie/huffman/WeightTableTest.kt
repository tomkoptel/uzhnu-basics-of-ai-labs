package uzhnu.edu.toie.huffman

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object WeightTableTest : Spek({
    describe("create") {
        it("should build priority queue based on weights") {
            val frequencies = mapOf(
                'E' to 15,
                'S' to 3
            )
            val queue = WeightTable.create(frequencies).queue
            queue.poll() shouldBeEqualTo ChildNode(value = 'S', weight = 3)
            queue.poll() shouldBeEqualTo ChildNode(value = 'E', weight = 15)
        }
    }

    describe("buildTreeHead") {
        it("should build a tree 18 -> (E, 15), (S, 3)") {
            val frequencies = mapOf(
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

        it("should build a complex balanced tree") {
            val frequencies = mapOf(
                'A' to 10,
                'E' to 15,
                'I' to 12,
                'S' to 3,
                'T' to 4,
                'P' to 13,
                '0' to 1
            )

            val queue = WeightTable.create(frequencies)
            val treeHead = queue.buildTreeHead() as ParentNode

            treeHead shouldBeEqualTo ParentNode(
                weight = 58,
                left = ParentNode(
                    weight = 25,
                    left = ChildNode(value = 'I', weight = 12),
                    right = ChildNode(value = 'P', weight = 13)
                ),
                right = ParentNode(
                    weight = 33,
                    left = ChildNode(value = 'E', weight = 15),
                    right = ParentNode(
                        weight = 18,
                        left = ParentNode(
                            weight = 8,
                            left = ChildNode(value = 'T', weight = 4),
                            right = ParentNode(
                                weight = 4,
                                left = ChildNode(value = '0', weight = 1),
                                right = ChildNode(value = 'S', weight = 3)
                            )
                        ),
                        right = ChildNode(value = 'A', weight = 10)
                    )
                )
            )
        }
    }
})

