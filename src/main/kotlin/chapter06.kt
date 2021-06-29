import java.util.*

class chapter06 {
}
fun main(){
//    try {
//        sample1()
//    }catch (e:Exception){
//        e.printStackTrace()
//    }
//    sample2()
//    sample3()
//    sample4()
//    sample5()
//    sample6()
//    sample7()
//    sample8()
//    sample9()
//    sample10()
//    sample11()
//    sample12()
//    sample13()
    sample14()
}

abstract class Book(val name: String)
inline fun <reified T> findFirst(books: List<Book>): T {
    val seleted = books.filter { book -> book is T }
    if (seleted.isEmpty()) {
        throw RuntimeException("Not found")
    }
    return seleted[0] as T
}
fun sample14() {
    class Fiction(name: String) : Book(name)
    class NonFiction(name: String) : Book(name)
    val books: List<Book> = listOf(Fiction("Moby Dick")
        , NonFiction("Learn to Code"), Fiction("LOTR"))
    val item:NonFiction = findFirst(books)
}

fun sample13() {
    abstract class Book(val name: String)

    class Fiction(name: String) : Book(name)
    class NonFiction(name: String) : Book(name)
    val books: List<Book> = listOf(Fiction("Moby Dick")
        , NonFiction("Learn to Code"), Fiction("LOTR"))


}

fun sample12() {
    fun printValues(values: Array<*>) {
        for (value in values) {
            println(value)
        }
    }

    printValues(arrayOf(1, 2))
}

fun sample11() {
    // 제약조건을 사용한다.
    fun <T : AutoCloseable> useAndClose(input: T) {
        input.close()
    }

    val stringWriter = java.io.StringWriter()
    stringWriter.append("hello")
    useAndClose(stringWriter)  // 정상

    fun <T> useAndClose(input: T) where T : AutoCloseable, T : Appendable {
        input.close()
        input.append("there")
    }
    val stringWriter2 = java.io.StringWriter()
    stringWriter2.append("hello")
    useAndClose(stringWriter2)  // 정상
}

fun sample10(){
    open class Fruit
    class Banana : Fruit()
    fun receiveFruit(fruit: Array<in Banana>) {
        println("Number of fruits: ${fruit.size}")
    }

    val anythings = Array<Any>(3) { _ -> Fruit() }
    receiveFruit(anythings)
}

fun sample9() {
    open class Fruit

    class Banana : Fruit()
    class Orange : Fruit()
    fun receiveFruitsMutable(fruits: Array<out Fruit>) {
        println("Number of fruits: ${fruits.size}")
    }

    val bananas: Array<Banana> = arrayOf()
    receiveFruitsMutable(bananas) // ERROR type이 맞지않다.
    val bananas2: Array<Fruit> = arrayOf()
    receiveFruitsMutable(bananas2) // ERROR type이 맞지않다.
}

fun sample8() {
    fun fetchMessage(id: Int): Any =
        if (id == 1) "Record found" else StringBuilder("data not found")

// error 발생 코드.
// StringBuilder는 String으로 캐스트되지 않는다.
    for (id in 1..2) {
        println("Message length: ${(fetchMessage(id) as? String)?.length ?:"----"}")
    }
}

fun sample7() {
    fun WhatToDo1(dayOfWeek: Any) = when (dayOfWeek) {
        "Saturday", "Sunday" -> "Relax"
        in listOf("Monday", "TuesDay", "Wednesday", "Thursday") -> "Work hard"
        in 2..4 -> "Work hard"
        "Friday" -> "Party"
        is String -> "What???"
        else -> "No clue"
    }
    fun WhatToDo2(dayOfWeek: Any) = when (dayOfWeek) {
        "Saturday", "Sunday" -> "Relax"
        in listOf("Monday", "TuesDay", "Wednesday", "Thursday") -> "Work hard"
        in 2..4 -> "Work hard"
        "Friday" -> "Party"
        is String -> "What, .... length ${dayOfWeek.length}"  // string smart cast
        else -> "No clue"
    }
    println(WhatToDo1("Sunday"))
    println(WhatToDo1("Monday"))
    println(WhatToDo1("test"))
    println(WhatToDo1(1))
    println(WhatToDo2("Sunday"))
    println(WhatToDo2("Monday"))
    println(WhatToDo2("test"))
    println(WhatToDo1(1))
}

fun sample6() {
    fun nickName4(name: String?) = when (name) {
        "Tom" -> "Coffee"
        null -> "Joker"
        else -> name.reversed().toUpperCase()
    }
    println(nickName4("Tom"))
    println(nickName4("test"))
    println(nickName4(null))
}

fun sample5() {
//    엘비스 연산자(?:)를 사용합니다.
    fun nickName(name: String?): String? {
        if (name == "Tom") {
            return "Coffee"
        }

        return name?.reversed()?.toUpperCase() ?: "Joker"
    }
    println(nickName("Tom"))
    println(nickName("test"))
    println(nickName(null))
}

fun sample4() {
//    safe-call 연산자를 이용해 toUpperCase도 적용하는 코드입니다.
    fun nickName(name: String?): String? {
        if (name == "Tom") {
            return "Coffee"
        }

        val result = name?.reversed()?.uppercase(Locale.getDefault())
        return if (result == null) "Joker" else result
    }
    println(nickName("test"))
    println(nickName(null))
}

fun sample3() {
//    개선1. safe-call 연산자
    fun nickName(name: String?): String? {
        if (name == "Tom") {
            return "Coffee"
        }

        return name?.reversed()  // safe-call 연산자
    }
    println(nickName("test"))
    println(nickName(null))
}

fun sample2() {
    fun nickName(name: String?):String? {
        if (name == "Tom") {
            return "Coffee"
        }
        if (name != null) {
            return name.reversed()
        }
        return null
    }
    println(nickName("test"))
    println(nickName(null))
}

fun sample1() {
    fun computeSqrt(n: Double): Double {
        if (n >= 0) {
            return Math.sqrt(n)
        } else {
            throw RuntimeException()  // Nothing 타입
        }
    }
   println(computeSqrt(-0.1))
}
