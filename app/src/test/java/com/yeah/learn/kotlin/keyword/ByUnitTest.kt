package com.yeah.learn.kotlin.keyword

import org.junit.Test
import kotlin.properties.Delegates
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

    //    /**
//     * lazy有三种mode
//     * [LazyThreadSafetyMode.SYNCHRONIZED]：默认值，通过synchronized(lock)来保证同时只有一个线程能初始化
//     * [LazyThreadSafetyMode.PUBLICATION]：多个线程会同时执行，初始化属性的函数会被多次调用，但是只有第一个返回的值被当做委托属性的值
//     * [LazyThreadSafetyMode.NONE]：线程不安全，但是减少了线程同步的开销
//     */
//    val mLazyValue: String by lazy(LazyThreadSafetyMode.NONE) {
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

    /**
     * 测试可观察属性
     */
    @Test
    fun testByObservable() {
        var observableValue: String by Delegates.observable("initialValue") { property, oldValue, newValue ->
            // 每次值的改变都会回调这里
            println("property = ${property.name}, oldValue = $oldValue, newValue = $newValue")
        }
        println("Before change value, observableValue = $observableValue")
        observableValue = "1"
        println("after change value, observableValue = $observableValue")
    }

    /**
     * 测试一个属性委托给另一个属性的类
     */
    class TestByOtherValueClazz {
        /**
         * 这个例子是：
         * 如果类中有一个属性被更名了，那么就可以把旧属性的赋值和取值委托给新属性
         * 这么做的好处就是不用修改原本引用旧属性的地方，也可以让那些地方使用新属性
         * P.S：这里委托的写法是 ${对象}::${新属性名}
         */
        @Deprecated("Use 'mNewStringValue' instead", ReplaceWith("mNewStringValue"))
        var mOldStringValue: String by this::mNewStringValue
        var mNewStringValue: String = ""
    }

    /**
     * 测试一个属性委托给另一个属性
     */
    @Test
    fun TestByOtherValue() {
        var testClazz = TestByOtherValueClazz()
        testClazz.mOldStringValue = "I am old data."
        println("mOldStringValue = ${testClazz.mOldStringValue}, mNewStringValue = ${testClazz.mNewStringValue}")
        testClazz.mNewStringValue = "I am new data."
        println("mOldStringValue = ${testClazz.mOldStringValue}, mNewStringValue = ${testClazz.mNewStringValue}")

        // 局部变量也能委托给某个对象的变量，同理某个对象的变量也能委托给别的对象的变量
        var tmpString: String by testClazz::mNewStringValue
        tmpString = "I am temp data."
        println("mOldStringValue = ${testClazz.mOldStringValue}, mNewStringValue = ${testClazz.mNewStringValue}, tmpString = $tmpString")
    }

}