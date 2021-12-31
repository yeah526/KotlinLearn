package com.yeah.learn.kotlin.buildinfunc

import org.junit.Test

/**
 * let用法
 *
 * @author: heweiyan
 * @date  : 2021/12/23
 */
class LetUnitTest {

    @Test
    fun testLetUsage() {
        val mayNullString : String? = null
//        val mayNullString : String? = "haha"
        val notNullString : String = mayNullString?.let {
            // let函数的返回类型是看最后一行的类型
            it
        } ?: "mayNullString is null."
        println(notNullString)
    }

}