package uzhnu.edu.toie.huffman

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object TreeBuilderTest : Spek({
    describe("buildATree") {
        val frequencies1: List<Pair<Char, Int>> = listOf(
            'A' to 10,
            'E' to 15,
            'I' to 12,
            'S' to 3,
            'T' to 4,
            'P' to 13,
            '0' to 1
        )

        xit("for frequencies=$frequencies1") {
            val table = FrequencyTable.create(frequencies1)

            val expected = HuffmanTree(
                head = ParentNode(weight = 58,
                    left = ParentNode(
                        weight = 25,
                        left = ChildNode(value = 'I', weight = 12),
                        right = ChildNode(value = 'P', weight = 13)
                    ),
                    right = ParentNode(
                        weight = 33,
                        left = ParentNode(
                            weight = 18,
                            left = ChildNode(value = 'A', weight = 10),
                            right = ParentNode(
                                weight = 8,
                                left = ChildNode(value = 'T', weight = 4),
                                right = ParentNode(
                                    weight = 4,
                                    left = ChildNode(value = '0', weight = 1),
                                    right = ChildNode(value = 'S', weight = 3)))
                        ),
                        right = ChildNode(value = 'E', weight = 15)
                    )
                ),
                table = FrequencyTable.EMPTY
            )
            buildATree(table) shouldBeEqualTo expected
        }

        val frequencies2: List<Pair<Char, Int>> = listOf(
            'A' to 10,
            'B' to 16,
            'C' to 3,
            'E' to 1,
            'D' to 4,
            'F' to 9
        )

        it("for frequencies=$frequencies2") {
            val table = FrequencyTable.create(frequencies2)
            val expected = HuffmanTree(
                head = ParentNode(
                    weight = 43,
                    left = ChildNode(value = 'B', weight = 16),
                    right = ParentNode(
                        weight = 27,
                        left = ParentNode(
                            weight = 17,
                            left = ChildNode(value = 'F', weight = 9),
                            right = ParentNode(
                                weight = 8,
                                left = ChildNode(value = 'D', weight = 4),
                                right = ParentNode(
                                    weight = 4,
                                    left = ChildNode(value = 'E', weight = 1),
                                    right = ChildNode(value = 'C', weight = 3)
                                )
                            )
                        ),
                        right = ChildNode(value = 'A', weight = 10)
                    )
                ),
                table = FrequencyTable.EMPTY
            )
            buildATree(table) shouldBeEqualTo expected
        }

        val frequencies3: List<Pair<Char, Int>> = listOf(
            'A' to 18,
            'B' to 16,
            'C' to 3,
            'E' to 1,
            'D' to 4,
            'F' to 9
        )

        xit("for frequencies=$frequencies3") {
            val table = FrequencyTable.create(frequencies3)
            println(buildATree(table))
        }
    }
})
