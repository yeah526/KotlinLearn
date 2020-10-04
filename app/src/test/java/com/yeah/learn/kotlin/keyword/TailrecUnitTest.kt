package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * 测试尾递归tailrec关键字
 *
 * @author: heweiyan
 * @date  : 2020/10/4
 */
class TailrecUnitTest {

    @Test
    fun testTailRec() {
        var num = 100
        println("recAdd() result = ${recAdd(num)}") //递归次数少的可以正常使用
//        println("result = ${recAdd(1000000)}") //递归次数多就会报堆栈溢出异常
        println("tailrecAdd() result = ${tailrecAdd(num)}") //使用尾递归优化后就不会报堆栈溢出
    }

    /**
     * 递归实现叠加运算
     * 没有使用tailrec关键字
     * 缺点是一旦传进来的数字过大(即递归层数过多)，会出现堆栈溢出(java.lang.StackOverflowError)的问题
     */
    fun recAdd(num: Int): Int {
//        println("recAdd(): num = $num")
        if (num == 1) {
            return 1;
        }
        return num + recAdd(num - 1)
    }

    /**
     * 尾递归实现叠加运算
     * 使用尾递归tailrec关键字优化，编译器最终会将该方法的实现由递归转换成循环，这样就可以解决堆栈溢出问题
     *
     * 如何查看tailrec编译优化后的代码？可以通过查看Kotlin -> Java后的代码，步骤如下：
     * 1、AS中打开kt文件
     * 2、点击最顶部工具栏的Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile
     */
    tailrec fun tailrecAdd(num: Int, result: Int = 0): Int {
//        println("tailrecAdd(): num = $num, result = ${result}")
        if (num == 1) {
            // 只能return不包含调用自身方法的操作 或 只包含调用自身方法的操作
            return 1 + result;
        }
        // 只能return不包含调用自身方法的操作 或 只包含调用自身方法的操作
        return tailrecAdd(num - 1, result + num);
    }

}