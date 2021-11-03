package com.yeah.learn.kotlin.basic

import org.junit.Test

/**
 * 测试尾递归tailrec关键字
 *
 * @author: heweiyan
 * @date  : 2020/10/4
 */
class LambdaUnitTest {

    @Test
    fun testLambda() {
        // 方法的声明和实现分开写
        val method1: () -> Unit
        val method2: (Int, Float) -> String
        // 方法的实现
        method1 = { println("I am method1.") }
        method1() //等价于method1.invoke()
        method2 = { param1, param2 -> "param1 = $param1; param2 = $param2" }
        println("method2 result: ${method2(1, 2.0f)}")

        // 方法的声明和实现一起写
        val method3: (Int, Float) -> String =
            { param1, param2 -> "param1 = $param1; param2 = $param2" }
        // method3的更简洁的写法
        val method4 = { param1: Int, param2: Float -> "param1 = $param1; param2 = $param2" }
        // method3的更接近非Lambda的写法
        val method5 =
            fun(param1: Int, param2: Float): String { return "param1 = $param1; param2 = $param2" }
        println("method3 result = ${method3(1, 2.0f)}")
        println("method4 result = ${method4(1, 2.0f)}")
        println("method5 result = ${method5(1, 2.0f)}")

        // 参数名_代表拒绝接收参数
        val method6: (Int, Float) -> String = { _, param2 ->
            "param1 = _; param2 = $param2"
        }
        println("method6 result = ${method6(1, 2.0f)}")
    }

}