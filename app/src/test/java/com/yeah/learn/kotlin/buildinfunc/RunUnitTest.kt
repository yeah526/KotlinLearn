package com.yeah.learn.kotlin.buildinfunc

import org.junit.Test

/**
 * run用法
 *
 * @author: heweiyan
 * @date  : 2021/12/31
 */
class RunUnitTest {

    @Test
    fun testRunUsage() {
        val test = StringBuilder("test")
        val result = test.run {
            // run函数持有的是this
            append("\nadd something")
            // run函数的返回类型是看最后一行的类型
            true
        }
        println("test = $test\nresult = $result")
    }

}