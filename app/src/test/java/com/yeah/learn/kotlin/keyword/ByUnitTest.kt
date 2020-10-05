package com.yeah.learn.kotlin.keyword

import org.junit.Test
import kotlin.reflect.KProperty

/**
 * 测试委托by关键字
 *
 * @author: heweiyan
 * @date  : 2020/10/4
 */
class ByUnitTest {

    /**
     * 委托
     */
    @Test
    fun testBy() {
        val buyHouse = BuyHouseProxy()
//        val buyHouse = BuyHouseProxy(Anjuke())
        buyHouse.buyHouse(90f)
    }

    /**
     * 买房子的接口
     */
    interface IBuyHouse {
        fun buyHouse(size: Float)
    }

    /**
     * 买房子的实现类：贝壳找房
     */
    class Beike : IBuyHouse {
        override fun buyHouse(size: Float) {
            println("买到了${size}平的房子（由贝壳找房提供）")
        }
    }

    /**
     * 买房子的实现类：安居客
     */
    class Anjuke : IBuyHouse {
        override fun buyHouse(size: Float) {
            println("买到了${size}平的房子（由安居客提供）")
        }
    }

    /**
     * 买房子的委托类
     * 通过by关键字可以快速实现委托模式
     */
//    class BuyHouseProxy : IBuyHouse by Beike()
    /**
     * BuyHouseProxy是委托类
     * 具体的实现是由Anjuke这个具体实现类来执行
     */
    class BuyHouseProxy : IBuyHouse by Anjuke()
//    /**
//     * 如果需要在代理中增强方法，需要在构造方法中把代理的对象传进来
//     */
//    class BuyHouseProxy(val buyHouseImpl: IBuyHouse) : IBuyHouse by buyHouseImpl {
//        override fun buyHouse(size: Float) {
//            println("买房前")
//            buyHouseImpl.buyHouse(size)
//            println("买房后")
//        }
//    }

    /**
     * 属性委托
     */
    @Test
    fun testByValue() {
        // 属性委托的本质
        // 转成Java代码后会发现这个valueDelegate其实并不是一个String变量，而是替换成了ValueDelegate对象
        // 因此看似是直接对String的取值和赋值操作，实际都被替换成了委托类的setValue()和getValue()方法
        var valueDelegate: String? by ValueDelegate()
        // 对String的取值操作，实际被替换成了ValueDelegate.getValue()方法
        println("mValueDelegate = ${valueDelegate}")
        // 对String的赋值操作，实际被替换成了ValueDelegate.setValue()方法
        valueDelegate = "abc"
        println("mValueDelegate = ${valueDelegate}")
    }

    /**
     * 属性委托类
     */
    class ValueDelegate<T> {

        // 真实的存储数据的变量
        var mRealValue: T? = null

        /**
         * 对于var和val类型的变量必须实现getValue()方法
         * 否则编译器会报错
         */
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            println("$thisRef, thank you for delegating '${property.name}' to me!")
            return mRealValue
        }

        /**
         * 对于var类型的变量必须实现setValue()方法
         * 否则编译器会报错
         */
        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
            mRealValue = value
        }
    }

    val mLazyValue: String by lazy {
        println("Start init lazyValue.")
        "test lazy value"
    }

    /**
     * 测试延迟属性
     */
    @Test
    fun testByLazy() {
        println("Before init mLazyValue")
        println("lazyValue = $mLazyValue")
    }

}