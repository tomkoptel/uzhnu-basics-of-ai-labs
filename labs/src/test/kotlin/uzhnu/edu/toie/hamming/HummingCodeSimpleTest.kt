package uzhnu.edu.toie.hamming

import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.fail

/**
 * Тест який реалізовує простий алгоритм кодування 4 бітного слова на 7 бітне слово.
 */
object HummingCodeSimpleTest : Spek({
    describe("кодування 4 на 7") {
        // Створюємо пару "Бітове слово, що кодуємо" та "Еталон порівняння"
        listOf(
            "0000" to "0000000",
            "0001" to "1101001",
            "0011" to "1000011",
            "0111" to "0001111",
            "1111" to "1111111",
            "1000" to "1110000",
            "1100" to "0111100",
            "1110" to "0010110",
            "0101" to "0100101"
        ).forEach { (code, encoded) ->
            it("слово біт $code, результат $encoded") {
                // Виймаємо біти на позиціях з права на ліво
                val x0 = code[3].toString().toInt()
                val x1 = code[2].toString().toInt()
                val x2 = code[1].toString().toInt()
                val x3 = code[0].toString().toInt()

                // Рахуємо контрольні групи 1, 2, 4
                val p1 = x3 xor x2 xor x0
                val p2 = x3 xor x1 xor x0
                val p4 = x2 xor x1 xor x0

                // Об'єднуємо результат в одну строку та порівнюємо з еталоном
                "$p1$p2$x3$p4$x2$x1$x0" shouldBeEqualTo encoded
            }
        }
    }
    describe("декодування 7 на 4") {
        // Створюємо пару кодованих коректних бітових слів та їх декодовану реалізацію
        listOf(
            "0000000" to "0000",
            "1101001" to "0001",
            "1000011" to "0011",
            "0001111" to "0111",
            "1111111" to "1111",
            "1110000" to "1000",
            "0111100" to "1100",
            "0010110" to "1110",
            "0100101" to "0101"
        ).forEach { (validCode, decodedCode) ->
            it("код $validCode помилок немає і презетує бітове слово $decodedCode") {
                // Виймаємо біти на позиціях з права на ліво
                val x0 = validCode[6].toString().toInt()
                val x1 = validCode[5].toString().toInt()
                val x2 = validCode[4].toString().toInt()
                val p4 = validCode[3].toString().toInt()
                val x3 = validCode[2].toString().toInt()
                val p2 = validCode[1].toString().toInt()
                val p1 = validCode[0].toString().toInt()

                // Рахуємо перевірочний біт для групи 1, 2, 4
                val c1 = p1 xor x3 xor x2 xor x0
                val c2 = p2 xor x3 xor x1 xor x0
                val c4 = p4 xor x2 xor x1 xor x0

                // Якщо хоча б один з перевірочних бітів 0 то тест не пройшов
                if (c1 != 0 && c2 != 0 && c4 != 0) {
                    fail("Код $validCode містить помилки")
                }

                decodedCode shouldBeEqualTo "$x3$x2$x1$x0"
            }
        }

        // Створюємо тріаду де перший код містить помилку, другий код скорегований код та масив позицій де є помилка
        listOf(
            Triple("0101001", "1101001", listOf(1)),
            Triple("1001001", "1101001", listOf(2)),
            Triple("1100001", "1101001", listOf(4)),
            Triple("0000001", "1101001", listOf(1, 2, 4))
        ).forEach { (invalidCode, correctedCode, expectedErrorPositions) ->
            it("код $invalidCode має помилку на позиціях ${expectedErrorPositions.joinToString()}") {
                // Виймаємо біти на позиціях з права на ліво
                val x0 = invalidCode[6].toString().toInt()
                val x1 = invalidCode[5].toString().toInt()
                val x2 = invalidCode[4].toString().toInt()
                val p4 = invalidCode[3].toString().toInt()
                val x3 = invalidCode[2].toString().toInt()
                val p2 = invalidCode[1].toString().toInt()
                val p1 = invalidCode[0].toString().toInt()

                // Рахуємо перевірочний біт для групи 1, 2, 4
                val c1 = p1 xor x3 xor x2 xor x0
                val c2 = p2 xor x3 xor x1 xor x0
                val c4 = p4 xor x2 xor x1 xor x0

                // Будуємо масив індексів(позицій), які мають помилку
                val errorsOnPositions = listOf(
                    c1 to 0,
                    c2 to 1,
                    c4 to 3
                )
                    // Відфільтровуємо 1, тобто позиції, що мають помилку
                    .filter { (errorCode, _) -> errorCode != 0 }
                    // Повертаємо позиції помилкових біті та відкидуєм біт перевірки
                    .map { (_, position) ->
                        println("Помилка на позиції ${position + 1}")
                        position
                    }
                // Індекси рахуються з 0 робимо трансформацію, щоб код влаштовував систему
                errorsOnPositions.map { it + 1 } shouldBeEqualTo expectedErrorPositions

                // Виправляємо помилку на вказаних позиція ревертуючи кожний біт
                val fixedCode = errorsOnPositions.fold(invalidCode) { modifiedCode, position ->
                    // Отримаємо біт по позиції
                    val bit = modifiedCode[position].toString().toInt()
                    // Перевертаємо біт
                    val reversedBit = if (bit == 1) 0 else 1
                    // Заміняємо помилковий біт на скоригованний
                    modifiedCode.replaceRange(position, position + 1, reversedBit.toString())
                }
                fixedCode shouldBeEqualTo correctedCode
            }
        }
    }
})
