/**
 * Use Function types and lambda expressions in kotlin.
 * @see https://developer.android.com/codelabs/basic-android-kotlin-compose-function-types-and-lambda
 *
 * Trick or treating
 */
fun main() {
    // To refer to a function as a value, use the function name followed by the function referrence operator(`::`).
    // val trickFunction = ::trick

    // `trick` now refers to a variable, rather than a function.
    // So it can be used as a value
    // val trickFunction = trick
    // trick()
    // trickFunction()
    // treat()

    val coins: (Int) -> String = { quantity ->
        // `return` is not allowed in a lambda expression.
        // and the last expression is the return value.
        "$quantity quarters"
    }
    val cupcake: (Int) -> String = {
        // quantity -> // They're not used, so they can be omitted.
        "Have a cupcake"
    }

    val treatFunction = trickOrTreat(false, coins)
    val trickFunction = trickOrTreat(true, cupcake)
    val treatFunction2 = trickOrTreat(false, null)
    treatFunction()
    trickFunction()
    treatFunction2()
    println()

    // Write lambda expressions with shorthand syntax.
    // 1. Omit parameter names:
    //     val coins: (Int) -> String = { "$it quarters" }
    // When it has only one parameter, the parameter name can be omitted.
    // And kotlin will use the default name `it`.
    val coins2: (Int) -> String = { "$it quarters" }
    val treat2 = trickOrTreat(false, coins2)
    treat2()
    println()
    // 2. Pass a lambda expression directly into a function.
    val treat3 = trickOrTreat(false, { "$it quarters" })
    treat3()
    println()
    // 3. Use trailing lambda syntax.
    // When a function type is the last parameter of a function, the lambda expression can be placed outside the parentheses.
    val treat4 = trickOrTreat(false) { "$it quarters" }
    treat4()
    println()

    // When a function returns a function or takes a function as a parameter, it is called a higher-order function.
    println("Use the `repeat()` function.")
    // `repeat()` is a higher-order function.
    // It has this function signature: `fun repeat(times: Int, action: (Int) -> Unit)`
    repeat(4) {
      treat4()
    }
}

/*
fun trick() {
  println("No treats!")
}
*/
// Rewrite the `trick()` function as a lambda expression.
val trick = { println("No treats!") }
/*
val name: (type1, type2, ...) -> returnType = { parameter1, parameter2, ... ->
  // body
}
 */

// Specify the data type of the lambda expression.
val treat: () -> Unit = {
  println("Have a treat!")
}

// Function type as a return type, and
// pass a function to another function as an argument.
// Nullable function type: ((parameters) -> returnType)?
fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        if (extraTreat != null) {
            println(extraTreat(5))
        }
        return treat
    }
}
