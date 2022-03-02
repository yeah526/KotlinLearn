package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * reified关键字是用于修饰inline函数的泛型
 * 被reified修饰的泛型才能获取到泛型的Class对象
 *
 * @author: heweiyan
 * @date  : 2022/3/2
 */
class ReifiedUnitTest {

    data class TestClass1(val info: String)
    data class TestClass2(val info: String)
    data class TestClass3(val info: String)

    private inline fun <reified T> test(classMismatchAction: (Any) -> T): T {
        println("T is ${T::class.java.simpleName}") //如果没有reified修饰，则报错
        val list = listOf(
            TestClass1("TestClass1"),
            TestClass2("TestClass2"),
            TestClass3("TestClass3"),
        )
        val result = list.shuffled().first()
        return result.takeIf { it is T } as T?
            ?: classMismatchAction(result)
    }

    @Test
    fun testReified() {
        println(
            "result = ${
                test {
                    TestClass1("TestClass1 because shuffled ${it::class.java.simpleName}")
                }
            }")
    }

}