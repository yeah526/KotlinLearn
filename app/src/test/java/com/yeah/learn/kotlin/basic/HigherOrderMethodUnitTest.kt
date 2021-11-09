package com.yeah.learn.kotlin.basic

import org.junit.Test

/**
 * 高阶函数用法
 * 函数中的参数包含函数变量，则可以说这个函数是个高阶函数
 *
 * @author: heweiyan
 * @date  : 2021/11/10
 */
class HigherOrderMethodUnitTest {

    @Test
    fun testHigherOrderMethod() {
        login("123", "123") {
            println("Login result = $it")
        }
    }

    private fun login(userName: String, password: String, callback: (result: Boolean) -> Unit) {
        println("Start login: userName = $userName; password = $password")
        callback(userName == password)
    }

}