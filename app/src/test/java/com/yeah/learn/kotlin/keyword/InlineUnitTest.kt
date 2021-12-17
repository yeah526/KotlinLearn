package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * 测试内联inline关键字
 * lambda会在调用处创建一个对象，性能上与Java的接口应该是无差别
 * 如果使用内联，则可以降低性能损耗，因为内联后的代码是相当于C++的宏定义/宏替换，会把代码替换到调用处，没有了对象的实例化性能损耗
 * 所以如果函数的参数有lambda，尽量使用内联
 *
 * @author: heweiyan
 * @date  : 2021/12/8
 */
class InlineUnitTest {

    @Test
    fun testInline() {
        login { result, msg -> System.out.println("result = $result; msg = $msg") }
    }

    /**
     * 可通过转化成Java代码看区别
     */
    //    private fun login(callback: (result: Int, msg: String) -> Unit) {
    private inline fun login(callback: (result: Int, msg: String) -> Unit) {
        Thread.sleep(1_000)
        callback(0, "Login success")
    }

}