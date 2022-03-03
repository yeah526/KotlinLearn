package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * infix可以给扩展函数加上中缀表达式的功能
 *
 * @author: heweiyan
 * @date  : 2022/3/3
 */
class InfixUnitTest {

    private infix fun <T> T.printAAndB(B: T) {
        println("A = $this; B = $B")
    }

    @Test
    fun testInfix() {
        "I am A.".printAAndB("I am B.") //普通方式
        "I am A." printAAndB "I am B." //中缀表达方式
    }

}