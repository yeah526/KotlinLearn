package com.yeah.learn.kotlin.basic

import org.junit.Test

/**
 * 扩展函数用法
 *
 * @author: heweiyan
 * @date  : 2021/11/9
 */
class ExtendMethodUnitTest {

    @Test
    fun testStringExtendMethod() {
        val string = "ABC"
        string.extendMethod1()
        string.extendMethod2()
    }

    private fun String.extendMethod1() {
        println("This is String extendMethod1(): this = $this")
    }

    private val extendMethod2 : String.() -> Unit = {
        println("This is String extendMethod2(): this = $this")
    }

    @Test
    fun testGenericsExtendMethod() {
        123.extendMethod()
        123.123f.extendMethod()
        "ABC".extendMethod()
        null.extendMethod()
    }

    private fun <T> T.extendMethod() {
        println("This is T extendMethod(): this is ${if(this == null) null else this!!::class.java}; this = $this")
    }

}