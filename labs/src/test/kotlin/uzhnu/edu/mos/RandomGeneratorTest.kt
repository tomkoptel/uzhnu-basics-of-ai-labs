package uzhnu.edu.mos

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger
import kotlin.random.Random.Default.nextInt

object RandomGeneratorTest : Spek({
    describe("Привести лістинг програми для отримання добутку 50 випадкових цілих чисел") {
        it("значення яких знаходяться в інтервалі [1, 100]") {
            // Генеруємо вектор розміром в 50 значень з випадковими значеннями від 1 до 100
            val randomValues = List(50) { nextInt(1, 100) }
            println("Виводимо лист всіх рандомних значень")
            randomValues.chunked(10).forEach {
                println(it)
            }
            // Створюємо зміну аггрегатор, котра буде зберігати наш добуток
            var accumulator = BigInteger("1")
            // Перебираємо в циклі всі значення вектору та генеруємо добуток
            randomValues.forEach { number ->
                val right = BigInteger(number.toString())
                accumulator = accumulator.multiply(right)
            }
            println("Добуток 50 випадкових цілих чисел -> $accumulator")
        }
    }
})
