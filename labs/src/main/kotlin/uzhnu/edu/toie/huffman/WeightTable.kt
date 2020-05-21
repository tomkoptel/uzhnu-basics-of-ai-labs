package uzhnu.edu.toie.huffman

import java.util.*

/**
 * Цей класс є відповідалним за створення "словника" символ та його бінарне слово.
 * Алгоритм будує бінарне дерево за принципом, символи що рідше всього зустрічаються
 * створюються залишаються на найнижчих рівнях дерева. Таким чином генурується найдовше
 * бінарне слово для найрідших символів.
 */
class WeightTable(
    val queue: Queue<Node>
) {
    /**
     * Будуємо "словник" символ та його бінарне слово.
     * Використовуємо рекурсію для навігації в середині
     * бінарного дерева.
     */
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
        /**
         * Дерево в даному випадку, це нода яка може бути або типу батько або типу дитина
         */
        when (tree) {
            is ParentNode -> {
                // Рухаємось в напрямку ліво та право
                @Suppress("NON_TAIL_RECURSIVE_CALL")
                buildR(dictionary, tree.left, suffix + "0")
                buildR(dictionary, tree.right, suffix + "1")
            }
            is ChildNode -> {
                // Ми добралися до кінця дерева, записуємо значення
                dictionary[tree.value] = suffix
            }
        }
    }

    /**
     * Власне сама реалізація Коду Гаффмана.
     */
    fun buildTreeHead(): ParentNode {
        // Створюємо пріорітетну чергу, яка само-балансується від найменшого до найвищого елемента
        val internalQueue = PriorityQueue(Node.Ascending).apply { addAll(queue) }

        // починаємо вибирати елементи з черги
        while (true) {
            val left = internalQueue.poll()
            val right = internalQueue.poll()
            // будуємо батьківську ноду
            internalQueue.offer(left + right)

            /**
             * якщо черга має один елемент виходимо з циклу
             *
             * Власне кожна ітерація об'єднує 2 ноди в одну батьківську поки вся черга не буде реперезентована одним об'єктом.
             */
            if (internalQueue.size == 1) {
                val head = internalQueue.poll()
                if (head is ParentNode) {
                    return head
                }
            }
        }
    }

    companion object {
        /**
         * Створюємо таблицю ваг на основі мапи "символ" та "частота" повторення в тексті.
         */
        fun create(frequencies: Map<Char, Int>): WeightTable {
            require(frequencies.isNotEmpty()) {
                "We can not work with empty map of character frequencies."
            }
            val queue = PriorityQueue(Node.Ascending)
            frequencies.forEach { (char, weight) ->
                queue.add(ChildNode(value = char, weight = weight))
            }
            // Наша черга вже збалансована від най менш важливого до найбільш важливого символа
            return WeightTable(queue)
        }
    }
}
