interface Expr
class Num(val value: Double): Expr
class Sum(val left: Expr, val right: Expr): Expr
class Sub(val left: Expr, val right: Expr): Expr
class Mul(val left: Expr, val  right: Expr): Expr
class Div(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Double = when(e) {
    is Num -> e.value
    is Sum -> eval(e.left) + eval(e.right)
    is Sub -> eval(e.left) - eval(e.right)
    is Mul -> eval(e.left) * eval(e.right)
    is Div -> eval(e.left) / eval(e.right)
    else -> throw IllegalArgumentException("Unknown Expression")
}

fun main() {
    // ((10 * 10) + 50) - (1000 / 100) = 140
    println(eval(
        Sub(
            Sum(
                Mul(
                    Num(10.0),
                    Num(10.0)
                ),
                Num(50.0)
            ),
            Div(
                Num(1000.0),
                Num(100.0)
            )
        )
    ))
}