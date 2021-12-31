package com.yeah.learn.kotlin.buildinfunc

import org.junit.Test

/**
 * @author: heweiyan
 * @date  : 2021/12/23
 */
class LetUnitTest {

    @Test
    fun testLetUsage() {
        val mayNullString : String? = null
//        val mayNullString : String? = "haha"
        val notNullString : String = mayNullString?.let {
            it
        } ?: "mayNullString is null."
        println(notNullString)
    }

}