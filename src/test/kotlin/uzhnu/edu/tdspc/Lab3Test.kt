package uzhnu.edu.tdspc

import kotlinx.coroutines.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

object Lab3Test : Spek({
    defaultTimeout = TimeUnit.MINUTES.toMillis(1)
    val slices = listOf(2, 4, 8, 16)

    describe("data set of 1k elements") {
        val array1 = (0 until 1_000).map { it }
        val array2 = (0 until 1_000).map { it + 200 }

        slices.forEach { slices ->
            it("parallel run $slices slices") {
                doParallelRun(array1, array2, numberOfSlices = slices)
            }
        }
        it("single thread run") {
            doOnMainThread(array1, array2)
        }
    }

    describe("data set of 10k elements") {
        val array1 = (0 until 10_000).map { it }
        val array2 = (0 until 10_000).map { it + 200 }

        slices.forEach { slices ->
            it("parallel run $slices slices") {
                doParallelRun(array1, array2, numberOfSlices = slices)
            }
        }
        it("single thread run") {
            doOnMainThread(array1, array2)
        }
    }

    describe("data set of 50k elements") {
        val array1 = (0 until 50_000).map { it }
        val array2 = (0 until 50_000).map { it + 200 }

        slices.forEach { slices ->
            it("parallel run $slices slices") {
                doParallelRun(array1, array2, numberOfSlices = slices)
            }
        }
        it("single thread run") {
            doOnMainThread(array1, array2)
        }
    }

    describe("data set of 100k elements") {
        val array1 = (0 until 100_000).map { it }
        val array2 = (0 until 100_000).map { it + 200 }

        slices.forEach { slices ->
            it("parallel run $slices slices") {
                doParallelRun(array1, array2, numberOfSlices = slices)
            }
        }
        it("single thread run") {
            doOnMainThread(array1, array2)
        }
    }
})

fun doParallelRun(array1: List<Int>, array2: List<Int>, numberOfSlices: Int) {
    val result = AtomicInteger(0)
    val millis = measureTimeMillis {
        val array1chunks = array1.chunked(size = array1.size / numberOfSlices)
        val array2chunks = array2.chunked(size = array2.size / numberOfSlices)

        runBlocking {
            array1chunks.forEach { list1 ->
                array2chunks.forEach { list2 ->
                    launch(Dispatchers.Default) {
                        val computed = computeCommonElements(list1, list2)
                        result.addAndGet(computed)
                    }
                }
            }
        }
    }

    println("result=$result")
    println("spent=$millis")
}

fun doOnMainThread(array1: List<Int>, array2: List<Int>) {
    val millis = measureTimeMillis {
        runBlocking {
            launch(Dispatchers.Default) {
                println("result=${computeCommonElements(array1, array2)}")
            }
        }
    }
    println("spent=$millis")
}

fun computeCommonElements(array1: List<Int>, array2: List<Int>): Int {
    var numOfCommon = 0
    array1.forEach { el1 ->
        array2.forEach { el2 ->
            if (el1 == el2) {
                numOfCommon++
            }
        }
    }
    return numOfCommon
}
