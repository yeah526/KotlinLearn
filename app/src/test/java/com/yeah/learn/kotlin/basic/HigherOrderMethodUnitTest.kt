package com.yeah.learn.kotlin.basic

import org.junit.Test

/**
 * 高阶函数用法
 * 如果函数中的参数或返回值带有函数，则可以说这个函数是个高阶函数
 *
 * @author: heweiyan
 * @date  : 2021/11/10
 */
class HigherOrderMethodUnitTest {

    @Test
    fun testHigherOrderMethod() {
        higherOrderMethod1("123", "123") {
            println("higherOrderMethod1 result = $it")
        }

        println("higherOrderMethod2 result = ${higherOrderMethod2("123", "234").invoke()}")
    }

    /**
     * 参数带有函数
     */
    private fun higherOrderMethod1(
        userName: String,
        password: String,
        callback: (result: Boolean) -> Unit
    ) {
        callback(userName == password)
    }

    /**
     * 返回值带有函数
     */
    private fun higherOrderMethod2(userName: String, password: String): () -> Boolean {
        return { userName == password }
    }

}