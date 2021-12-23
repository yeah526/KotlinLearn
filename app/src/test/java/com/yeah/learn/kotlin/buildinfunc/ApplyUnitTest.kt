package com.yeah.learn.kotlin.buildinfunc

import org.junit.Test

/**
 * apply用法
 *
 * @author: heweiyan
 * @date  : 2021/12/23
 */
class ApplyUnitTest {

    @Test
    fun testApplyUsage() {
        val sb = StringBuilder("test")
            .apply {
                // 一般的匿名函数都是持有it，而apply是持有this，所以在apply代码块内可以直接调用被apply的对象的方法
                // 做到这个的区别的方法就是apply在调用匿名函数时，不会把被apply对象当作参数传到代码块内
                // 又因为apply是返回被apply的对象，所以一般用于对同一个对象的链式调用
                append("\nadd something1")
            }
            .apply { append("\nadd something2") }
            .apply { append("\nadd something3") }
            .apply { append("\nadd something4") }
        println("$sb")
    }

}