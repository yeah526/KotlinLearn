package com.yeah.learn.kotlin.keyword

import org.junit.Test

/**
 * @author: heweiyan
 * @date  : 2022/3/2
 */
class InOutUnitTest {

    open class Person
    class Man : Person()
    class Woman : Person()

    //    interface Producer<T> {
    interface Producer<out T> { //相当于Java的：interface Producer<? extends T>
        fun produce(): T

//        fun canNotWriteT(item: T) //因为T是out，无法输入，所以编译会报错
    }

    class PersonProducer : Producer<Person> {
        override fun produce(): Person {
            return Person()
        }
    }

    class ManProducer : Producer<Man> {
        override fun produce(): Man {
            return Man()
        }
    }

    class WomanProducer : Producer<Woman> {
        override fun produce(): Woman {
            return Woman()
        }
    }

    @Test
    fun testOut() {
        // 泛型的默认情况下，泛型的子类对象无法赋值给泛型的父类对象
        // 加上了out，泛型的子类对象就可以赋值给泛型的父类对象
        val producer1: Producer<Person> = PersonProducer()
        val producer2: Producer<Person> = ManProducer()
        val producer3: Producer<Person> = WomanProducer()
    }

    //    interface Consumer<T> {
    interface Consumer<in T> { //相当于Java的：interface Producer<? super T>
        fun consume(item: T)

//        fun canNotReadT(): T //因为T是in，无法输出，所以编译会报错
    }

    class PersonConsumer : Consumer<Person> {
        override fun consume(item: Person) {
            println("PersonConsumer: item = $item")
        }
    }

    class ManConsumer : Consumer<Man> {
        override fun consume(item: Man) {
            println("ManConsumer: item = $item")
        }
    }

    class WomanConsumer : Consumer<Woman> {
        override fun consume(item: Woman) {
            println("WomanConsumer: item = $item")
        }
    }

    fun testIn() {
        // 泛型的默认情况下，泛型的父类对象无法赋值给泛型的子类对象
        // 加上了in，泛型的父类对象就可以赋值给泛型的子类对象
        val consumer1: Consumer<Person> = PersonConsumer()
        val consumer2: Consumer<Man> = PersonConsumer()
        val consumer3: Consumer<Woman> = PersonConsumer()
    }

}