package com.yeah.learn.kotlin.collection

import org.junit.Test

/**
 * List用法
 *
 * @author: heweiyan
 * @date  : 2022/2/25
 */
class ListUnitTest {

    @Test
    fun testListGetUsage() {
        val list = listOf(1, 2, 3, 4, 5)

        // 使用getOrElse()，如果越界，则返回lambda中的最后一行
        val getOrElseResult = list.getOrElse(6) {
            println("List getOrElse(): index out of range, index = $it")
            "Index out of range."
        }
        println("getOrElseResult = $getOrElseResult")

        // 使用getOrNull()，如果越界，则返回null
        val getOrNullResult = list.getOrNull(6)
        println("getOrNullResult = $getOrNullResult")
    }

    @Test
    fun testListModifyUsage() {
        val list = mutableListOf(1, 2, 3, 4, 5)

        // 操作符重载
        list += 6
        list -= 5
        println("list = $list")

        list.removeIf { it % 2 == 0 } //这里的it是具体的值，而不是index
        println("list = $list")
    }

    @Test
    fun testListTraversalUsage() {
        val list = mutableListOf(1, 2, 3, 4, 5)

        // 方式1
        for (i in list) {
            print("$i, ")
        }
        println()
        for ((index, i) in list.withIndex()) {
            println("index = $index; i = $i")
        }

        // 方式2，实际跟方式1没有任何区别
        list.forEach {
            print("$it, ")
        }
        println()
        list.forEachIndexed { index, i -> println("index = $index; i = $i") }
    }

    @Test
    fun testListDeconstructUsage() {
        val list = mutableListOf(1, 2, 3, 4, 5)
        val (val1, val2, val3, val4, val5) = list
//        val (val1, val2, val3, val4, val5) = list //解构要注意越界问题，不然会报错
        println("val1 = $val1, val2 = $val2, val3 = $val3, val4 = $val4, val5 = $val5")
    }

}
