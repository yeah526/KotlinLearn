package com.yeah.learn.kotlin.basic

import org.junit.Test

/**
 * 解构声明
 *
 * @author: heweiyan
 * @date  : 2021/12/21
 */
class DestructuringDeclarationUnitTest {

    @Test
    fun testDestructuringDeclaration() {
//        val (name1, name2) = arrayOf("LiLei") //会抛出java.lang.ArrayIndexOutOfBoundsException
        val (name1, name2) = arrayOf("LiLei", "HanMeiMei")
//        val (name1, name2) = arrayOf("LiLei", "HanMeiMei", "Tom") //这种是可以的
        println("name1 = $name1, name2 = $name2")
    }

}