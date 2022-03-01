package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * @author: heweiyan
 * @date  : 2022/2/28
 */
class LateinitUnitTest {

    private lateinit var test: String

    @Test
    fun testLateinit() {
//        println("test = $test") //未初始化就使用，会报错
        if (::test.isInitialized) {
            println("test = $test")
        } else {
            println("test NOT init.")
            test = "abc"
        }
        println("test = $test")
    }

}